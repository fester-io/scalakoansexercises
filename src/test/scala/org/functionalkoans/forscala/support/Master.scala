package org.functionalkoans.forscala.support

import org.scalatest.Stopper

object Master extends Stopper {

  @volatile private[this] var studentNeedsToMeditate = false

  case class HasTestNameAndSuiteName(suiteName: String, testName: String)

  def studentFailed(event: HasTestNameAndSuiteName): String = {
    studentNeedsToMeditate = true
    meditationMessage(event)
  }

  private def meditationMessage(event: HasTestNameAndSuiteName) =
    s"""Please meditate on koan "${event.testName}" of suite "${event.suiteName}""""

  override def stopRequested = studentNeedsToMeditate
  override def requestStop() = studentNeedsToMeditate = true
}
