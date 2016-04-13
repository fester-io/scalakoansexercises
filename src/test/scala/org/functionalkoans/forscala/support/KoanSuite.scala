package org.functionalkoans.forscala.support

import org.functionalkoans.forscala.support.Master.HasTestNameAndSuiteName
import org.scalatest._
import org.scalatest.events._
import org.scalatest.exceptions.TestPendingException
import org.scalatest.matchers.BeMatcher


object KoanSuite extends KoanSuite


trait KoanSuite extends FunSuite with Matchers {

  def koan(name: String)(fun: => Unit) = test(name.stripMargin('|'))(fun)

  def meditate() = pending

  def __ : BeMatcher[Any] = throw new TestPendingException

  protected class ___ extends Exception {
    override def toString = "___"
  }

  class ReportToTheMaster(other: Reporter) extends Reporter {
    @volatile var failed = false
    def failure(event: HasTestNameAndSuiteName) = {
      failed = true
      note("*****************************************")
      note("*****************************************")
      note("")
      note("")
      note("")
      note(Master.studentFailed(event))
      note("")
      note("")
      note("")
      note("*****************************************")
      note("*****************************************")
    }

    def apply(event: Event) = event match {
      case e: TestIgnored ⇒ failure(HasTestNameAndSuiteName(e.suiteName, e.testName))
      case e: TestFailed ⇒ failure(HasTestNameAndSuiteName(e.suiteName, e.testName))
      case e: TestPending ⇒ failure(HasTestNameAndSuiteName(e.suiteName, e.testName))
      case _ ⇒ other(event)
    }
  }

  override protected def runTest(testName: String, args: Args) =
    if (!Master.stopRequested)
      super.runTest(testName, args.copy(reporter = new ReportToTheMaster(args.reporter), stopper = Master))
    else
      SucceededStatus
}
