package org.functionalkoans.forscala

import org.functionalkoans.forscala.support.KoanSuite

import scala.annotation.tailrec

class AboutMethods extends KoanSuite {

  koan(
    """A method's last statement will be what is returned.
      | There is no need for the keyword `return`.
      | When a method includes a `=` after the method declaration that
      | will infer the return type""".stripMargin) {
    def add(x: Int, y: Int) = {
      x + y
    }
    add(6, 7) should be(__)
  }

  koan(
    """If you want to include the return type explicitly,
      | no one will stop you""".stripMargin) {
    def add(x: Int, y: Int): Int = {
      //Notice the :Int at the end of the method
      x + y
    }
    add(2, 10) should be(__)
  }


  koan(
    """If a method returns two different types and no explicit
      | return type is defined,
      | the type of the method will likely be inferred as the
      | common super parent of the two types being returned""".stripMargin) {

    def add(x: Int, y: Int) = {
      //implicitly the return type is Any
      if (x > 10) (x + y).toString
      else x + y
    }

    add(2, 10) should be(__)
    add(1, 1) should be(__)
  }

  /** @note This is considered bad practice, use = syntax! */
  koan("""If a method does not of have equal it is considered `Unit` which is analogous to `void` in Java""") {
    def foo(x: Int) {
      //Note: No `=`
      (x + 4) should be(__)
    }
    foo(5)
  }

  koan(
    """If you want to have an = on the method, while still explicitly returning Unit you can make the return type
      |`Unit`, this is also analogous to `void""") {
    def foo(x: Int): Unit = {
      //Note we are declaring Unit
      (x + 4) should be(__)
    }
    foo(3)
  }

  koan("""Once you have an =, it is understood that there will be a return type and can be inferred""") {
    def foo(x: Int) = 3 + 4
    foo(3).isInstanceOf[Int] should be(__) //.isInstanceOf[...] is analogous to Java's instanceOf
  }

  koan("""Of course if you wish to be explicit about the return type, you can attach it at the end of the method""") {
    def foo(x: Int): Int = 3 + 4
    foo(3).isInstanceOf[Int] should be(__)
  }

  koan(
    """Remember you can have strange characters in values and variables as long as they're
      | after an underscore, well you can do the same in methods""") {

    class Pennies(val n: Int)
    def doYouHaveAnySpareChange_?() = new Pennies(25)
    doYouHaveAnySpareChange_?().n should be(__)
  }

  koan(
    """If you also remember you can add reserved words or words with space to any value or variable?
      | Same applies for methods. Although please note that this is uncommon unless you really are into
      | internal DSLs""") {

    class Employee(val `first name`: String, val `last name`: String, val `employee status`: String)

    def `put employee on probation`(employee: Employee) = {
      new Employee(employee.`first name`, employee.`last name`, "Probation")
    }

    val probationEmployee = `put employee on probation`(new Employee("Milton", "Waddams", ""))
    probationEmployee.`employee status` should be(__)
  }

  koan(
    """Convention (not required for the compiler) states that if you a call a method that
      |returns a Unit, invoke that method with empty parenthesis, other leave the parenthesis out""") {

    def add(a: Int, b: Int) = a + b //implied return type of Int!
    def performSideEffect(): Unit = System.currentTimeMillis

    add(4, 6) should be(__)
    performSideEffect() //Notice the parenthesis, since the method we called is Unit!
  }

  koan(
    """Methods with colons are right-associative, that means the object that a method is on will be on
      | the _right_ and the method parameter will be on the _left_""") {

    class Foo(y: Int) {
      def ~:(n: Int) = n + y + 3
    }

    val foo = new Foo(9)
    10 ~: foo should be(__)
    foo.~:(40) should be(__)
  }
}
