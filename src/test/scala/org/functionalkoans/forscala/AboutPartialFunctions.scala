package org.functionalkoans.forscala

import org.functionalkoans.forscala.support.KoanSuite
import org.scalatest.Matchers

class AboutPartialFunctions extends KoanSuite with Matchers {

  koan(
    """A partial function is a trait that when
      | implemented can be used as building blocks to determine
      | a solution.  The trait PartialFunction requires that the
      | the method isDefinedAt and apply be implemented.""") {

    val doubleEvens: PartialFunction[Int, Int] = new PartialFunction[Int, Int] {
      //States that this partial function will take on the task
      def isDefinedAt(x: Int) = x % 2 == 0

      //What we do if this does partial function matches
      def apply(v1: Int) = v1 * 2
    }

    val tripleOdds: PartialFunction[Int, Int] = new PartialFunction[Int, Int] {
      def isDefinedAt(x: Int) = x % 2 != 0

      def apply(v1: Int) = v1 * 3
    }

    val whatToDo = doubleEvens orElse tripleOdds //Here we chain the partial functions together

    whatToDo(3) should be(__)
    whatToDo(4) should be(__)

    //Note: Fire up a REPL and try doubleEvens(7) now!
  }

  koan(
    """Case statements are a quick way to create partial functions. When you create a case
      | statement, the apply and isDefinedAt is created for you.""") {

    //The case statements are called case statements with guards
    val doubleEvens: PartialFunction[Int, Int] = {
      case x: Int if (x % 2) == 0 => x * 2
    }
    val tripleOdds: PartialFunction[Int, Int] = {
      case x: Int if (x % 2) != 0 => x * 3
    }

    val whatToDo = doubleEvens orElse tripleOdds //Here we chain the partial functions together
    whatToDo(3) should be(__)
    whatToDo(4) should be(__)

    //Note: Fire up a REPL and try doubleEvens(7) now!
  }

  koan(
    """The result of partial functions can have an \'andThen\' function added to the end
      | of the chain""") {

    //These are called case statements with guards
    val doubleEvens: PartialFunction[Int, Int] = {
      case x: Int if (x % 2) == 0 => x * 2
    }
    val tripleOdds: PartialFunction[Int, Int] = {
      case x: Int if (x % 2) != 0 => x * 3
    }

    val addFive = (x: Int) => x + 5
    val whatToDo = doubleEvens orElse tripleOdds andThen addFive //Here we chain the partial functions together
    whatToDo(3) should be(__)
    whatToDo(4) should be(__)
  }

  koan(
    """The result of partial functions can have an \'andThen\' function added to the end
      | of the chain used to continue onto another chain of logic""") {

    val doubleEvens: PartialFunction[Int, Int] = {
      case x: Int if (x % 2) == 0 => x * 2
    }
    val tripleOdds: PartialFunction[Int, Int] = {
      case x: Int if (x % 2) != 0 => x * 3
    }

    val printEven: PartialFunction[Int, String] = {
      case x: Int if (x % 2) == 0 => "Even"
    }
    val printOdd: PartialFunction[Int, String] = {
      case x: Int if (x % 2) != 0 => "Odd"
    }

    val whatToDo = doubleEvens orElse tripleOdds andThen (printEven orElse printOdd)

    whatToDo(3) should be(__)
    whatToDo(4) should be(__)
  }

  koan(
    """Using this pattern provides a nice functional alternative for the ''chain-of-responsibility''
      | pattern known from object oriented programming""".stripMargin) {
    import ChainOfResponsibility._

    val president = new President()
    val vp = new VicePresident(Some(president))
    val director = new Director(Some(vp))
    val manager = new Manager(Some(director))

    manager.processRequest(PurchaseRequest(4000, "General")) should be(__)
    manager.processRequest(PurchaseRequest(7000, "General")) should be(__)
    manager.processRequest(PurchaseRequest(16000, "General")) should be(__)
    manager.processRequest(PurchaseRequest(22000, "General")) should be(__)

    val `manager pf`: PartialFunction[PurchaseRequest, String] = {
      case x if x.amount < 5000 ⇒ s"Manager will approve $$${x.amount}"
    }

    val `director pf`: PartialFunction[PurchaseRequest, String] = {
      case x if x.amount < 10000 ⇒ s"Director will approve $$${x.amount}"
    }

    val `vp pf`: PartialFunction[PurchaseRequest, String] = {
      case x if x.amount < 20000 ⇒ s"Vice President will approve $$${x.amount}"
    }

    val `president pf`: PartialFunction[PurchaseRequest, String] = {
      case x if x.amount < 30000 ⇒ s"President will approve $$${x.amount}"
      case x ⇒ s"Your request for $$${x.amount} needs a board meeting!"
    }

    val cor = `manager pf` orElse `director pf` orElse `vp pf` orElse `president pf`

    cor(PurchaseRequest(4000, "General")) should be(__)
    cor(PurchaseRequest(7000, "General")) should be(__)
    cor(PurchaseRequest(16000, "General")) should be(__)
    cor(PurchaseRequest(22000, "General")) should be(__)
  }
}

/** == Chain of responsibility pattern ==
  * Chain of responsibility like it would be done in Java (or other OO languages).<br/>
  * Now, although this lacks a lot of the verbosity of Java, it is still a lot of boilerplate.
  */
object ChainOfResponsibility {
  case class PurchaseRequest(amount: Double, purpose: String)

  abstract class PurchasePower(protected val successor: Option[PurchasePower]) {
    final val Base = 500.0d
    def processRequest(request: PurchaseRequest): Option[String]
  }

  class Manager(successor: Option[PurchasePower] = None) extends PurchasePower(successor) {
    final val Allowable = 10 * Base
    override def processRequest(request: PurchaseRequest) =
      if (request.amount < Allowable) Some(s"Manager will approve $$${request.amount}")
      else successor flatMap (_.processRequest(request))
  }

  class Director(successor: Option[PurchasePower] = None) extends PurchasePower(successor) {
    final val Allowable = 20 * Base
    override def processRequest(request: PurchaseRequest) =
      if (request.amount < Allowable) Some(s"Director will approve $$${request.amount}")
      else successor flatMap (_.processRequest(request))
  }

  class VicePresident(successor: Option[PurchasePower] = None) extends PurchasePower(successor) {
    final val Allowable = 40 * Base
    override def processRequest(request: PurchaseRequest) =
      if (request.amount < Allowable) Some(s"Vice President will approve $$${request.amount}")
      else successor flatMap (_.processRequest(request))
  }

  class President(successor: Option[PurchasePower] = None) extends PurchasePower(successor) {
    final val Allowable = 60 * Base
    override def processRequest(request: PurchaseRequest) =
      if (request.amount < Allowable) Some(s"President will approve $$${request.amount}")
      else Some(s"Your request for $$${request.amount} needs a board meeting!")
  }
}
