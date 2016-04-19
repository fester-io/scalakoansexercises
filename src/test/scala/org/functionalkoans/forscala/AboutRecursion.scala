package org.functionalkoans.forscala

import org.functionalkoans.forscala.support.KoanSuite

import scala.annotation.tailrec

class AboutRecursion extends KoanSuite {

  koan("""When performing recursion, the return type on the method is mandatory!""") {

    def factorial(x: BigInt): BigInt = {
      //Notice the return type of BigInt!
      if (x <= 1) 1
      else x * factorial(x - 1)
    }

    factorial(4) should be(__) // List(...) is how a list is created more about lists later.

    //Note: Fire up a REPL and paste factorial(100000)!
  }

  koan(
    """If you want to ensure a method is not only recursive but _tail recursive_,
      | you can get help from the scala compiler to ensure that it is indeed a
      | tail recursive call by
      | including scala.annotation.tailrec on the method.  When methods are properly tail recursive, the
      | Scala compiler will optimize the code from stack recursion into a loop at compile time""") {

    import scala.annotation.tailrec
    //importing annotation!
    @tailrec
    def fact(i: BigInt, accumulator: BigInt): BigInt = {
      // This is an accumulator to ensure tail recursion!
      if (i <= 1)
        accumulator
      else
        fact(i - 1, i * accumulator)
    }

    def factorial(i: BigInt): BigInt = {
      fact(i, 1)
    }

    factorial(4) should be(__)

    //Note: Fire up a REPL and try factorial(100000) now!
  }

  koan(
    """In scala, methods can be placed inside in methods! This comes useful for
      | recursion where accumulator helper methods can be placed inside the outer
      | method, or you just want to place one method in another for design reasons""") {

    // Reminder: 5! = 1 x 2 x 3 x 4 x 5 = 120

    def factorial(i: BigInt): BigInt = {
      @tailrec
      def fact(i: BigInt, accumulator: BigInt): BigInt = {
        if (i <= 1)
          accumulator
        else
          fact(i - 1, i * accumulator)
      }
      fact(i, 1)
    }

    factorial(0) should be(__)
    factorial(1) should be(__)
    factorial(2) should be(__)
    factorial(3) should be(__)
  }

  koan("""The former method could of course also be written with a pattern match""") {
    def factorial(i: BigInt) = {
      @tailrec def fact(i: BigInt, acc: BigInt = 1): BigInt = i match {
        case x if x <= 1 ⇒ acc
        case x ⇒ fact(x - 1, x * acc)
      }

      fact(i)
    }

    factorial(5) should be(__)
  }

  koan(
    """As a precaution, the helpful @tailrec annotation will throw a compile time if a method is not tail recursive,
      | meaning that the last call and only call of the method is the recursive method. Scala optimizes recursive calls
      | to a loop from a stack""") {

    //    @tailrec   //Uncomment this like to see the result, then comment it again and answer the koan
    def fibonacci(n: Int): Int = {
      if (n <= 1)
        1
      else
        fibonacci(n - 1) + fibonacci(n - 2)
    }

    //Reminder fibonacci sequence: 1, 1, 2, 3, 5, 8, 13, 21
    fibonacci(4) should be(__)
  }

  koan(
    """As properly tail recursive method will use an accumulator method so that the only call of a recursive method
      |is the last one.
      | just like the first koan above.""") {


    def fibonacci(n: Int) = {
      @tailrec
      def fib(n: Int, b: Int, a: Int): Int = n match {
        case 0 => a
        case _ => fib(n - 1, a + b, b)
      }

      fib(n, 1, 0)
    }

    //Reminder fibonacci sequence: 1, 1, 2, 3, 5, 8, 13, 21
    fibonacci(4) should be(__)
  }
}
