package org.functionalkoans.forscala.support

import org.scalatest._
import org.scalatest.events._
import org.scalatest.exceptions.TestPendingException
import org.scalatest.matchers.BeMatcher

trait KoanSuite extends FunSuite with Matchers {

  def koan(name: String)(fun: => Unit) {
    test(name.stripMargin('|'))(fun)
  }

  def meditate() = pending

  def __ : BeMatcher[Any] = {
    throw new TestPendingException
  }

  protected class ___ extends Exception {
    override def toString = "___"
  }

  private class ReportToTheMaster(other: Reporter) extends Reporter {
    var failed = false
    def failure(event: Master.HasTestNameAndSuiteName) {
      failed = true
      info("*****************************************")
      info("*****************************************")
      info("")
      info("")
      info("")
      info(Master.studentFailed(event))
      info("")
      info("")
      info("")
      info("*****************************************")
      info("*****************************************")
    }

    def apply(event: Event) {
      event match {
        case e: TestIgnored => failure(event.asInstanceOf[Master.HasTestNameAndSuiteName])
        case e: TestFailed => failure(event.asInstanceOf[Master.HasTestNameAndSuiteName])
        case e: TestPending => failure(event.asInstanceOf[Master.HasTestNameAndSuiteName])
        case _ => other(event)
      }

    }
  }

  protected def runTest(
      testName: String,
      reporter: Reporter,
      stopper: Stopper,
      configMap: Map[String, Any],
      tracker: Tracker) {
    if (!Master.studentNeedsToMeditate) {
      super.runTest(testName,
        Args(reporter = new ReportToTheMaster(reporter),
          stopper = Master,
          configMap = ConfigMap(configMap.toSeq: _*),
          tracker = tracker))
    }
  }
}
