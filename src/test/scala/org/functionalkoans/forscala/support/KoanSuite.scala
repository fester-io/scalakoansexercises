package org.functionalkoans.forscala.support

import org.functionalkoans.forscala.support.Master.HasTestNameAndSuiteName
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
        case e: TestIgnored ⇒ failure(HasTestNameAndSuiteName(e.testName, e.suiteName))
        case e: TestFailed ⇒ failure(HasTestNameAndSuiteName(e.testName, e.suiteName))
        case e: TestPending ⇒ failure(HasTestNameAndSuiteName(e.testName, e.suiteName))
        case _ ⇒ other(event)
      }

    }
  }

  override def run(testName: Option[String], args: Args) =
    if (!Master.stopRequested)
      super.run(testName, args.copy(reporter = new ReportToTheMaster(args.reporter), stopper = Master))
    else
      FailedStatus

  override protected def runTest(testName: String, args: Args) =
    if (!Master.stopRequested)
      super.runTest(testName, args.copy(reporter = new ReportToTheMaster(args.reporter), stopper = Master))
    else
      FailedStatus
}
