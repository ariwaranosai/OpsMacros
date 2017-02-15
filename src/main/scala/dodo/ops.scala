package dodo

/**
  * Created by ariwaranosai on 2017/2/15.
  *
  */
import scala.language.experimental.macros
import scala.language.higherKinds
import scala.reflect.macros.blackbox.Context

trait Ops {
  def operatorNames: Map[String, String]

  def binop[A, B](c: Context)(rhs: c.Expr[A]): c.Expr[B] = {
    import c.universe._
    val (ev, lhs) = c.prefix.tree match {
      case q"$_($lhs)($ev)" => (ev, lhs)
      case t => c.abort(c.enclosingPosition,
        "Cannot extract subject of operator (tree = %s)" format t)
    }

    val s = c.macroApplication.symbol.name.toString

    val method = TermName(operatorNames.getOrElse(s, s))

    c.Expr(q"$ev.$method($lhs, ${rhs.tree})")
  }
}

trait DefaultOperatorNames {

  val operatorNames = Map(
    // Eq (===)
    ("$eq$eq$eq", "eqv"),
    ("$bar$times$bar", "times")
  )
}

object DefaultOps extends Ops with DefaultOperatorNames
