package org.functionalkoans.forscala

import org.functionalkoans.forscala.support.KoanSuite
import org.scalatest.Matchers


/** = Methods are not functions =
  * Explains the difference between methods and functions and provides exercises to prove them.
  *
  * @note TL;DR ... Methods in Scala are not values, but functions are. You can construct a function that delegates
  *       to a method via η-expansion (triggered by the trailing underscore thingy).
  * @note For the whole article, point your browser to [[https://tpolecat.github.io/2014/06/09/methods-functions.html]]
  * @author jtb
  */
class AboutMethodsVsFunctions extends KoanSuite with Matchers {

  koan(
    """The definition that will be used here is that a ''method'' is something defined
      | with `def` and a ''value'' is something defined with `val`""".stripMargin) {
    def add1(n: Int): Int = n + 1
    val add1f: Int ⇒ Int = n ⇒ n + 1

    add1(3) should be(__)
    add1f(3) should be(__)

    val `add1 fn` = new Function1[Int, Int] {
      def apply(n: Int) = n + 1
    }

    `add1 fn`.isInstanceOf[Int ⇒ Int] should be(__)
    add1f.isInstanceOf[Int ⇒ Int] should be(__)

    /*
     * Note: open up a REPL and observe the following
     * > def add1(n: Int): Int = n + 1
     * add1: (n: Int)Int
     * > val f = add1
     *
     * You will notice that a method can not be assigned to a val
     * Note also the ''type'' of `add1`, which doesn't look normal; you can't declare a variable of type `(n: Int)Int`
     * Methods are not values!
     */
  }

  koan(
    """By adding the η-expansion postfix operator (η is pronounced "eta"),
      | we can turn the method into a function value""".stripMargin) {
    def add1(n: Int): Int = n + 1
    val f = add1 _

    add1(3) should be(__)
    f(3) should be(__)
    f.isInstanceOf[Int ⇒ Int] should be(__)

    // Note: open up a REPL and observe the type of `f`
  }
}
