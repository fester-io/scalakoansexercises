package io.fester.util

object OctalLiterals {

  implicit class OctalContext(sc: StringContext) {
    def o(): Int = macro octalImpl
  }

  def octalImpl(c: scala.reflect.macros.blackbox.Context)(): c.Expr[Int] = {
    import c.universe._

    c.Expr(
      q"""${
        c.prefix.tree match {
          case Apply(_, Apply(_, Literal(Constant(oct: String)) :: Nil) :: Nil) ⇒ Integer.decode("0" + oct).toInt
          case _ ⇒ c.abort(c.enclosingPosition, "Invalid octal literal.")
        }
      }""")
  }
}
