package bm

import dodo.DefaultOps

import scala.language.experimental.macros
import scala.{specialized => sp}


/**
  * Created by ariwaranosai on 2017/2/15.
  *
  */

trait Multi[@sp A] {
  def times(lhs: A, rhs: A): A
}

object Multi {
  implicit val intEq = new Multi[Int] {
    @inline
    override def times(lhs: Int, rhs: Int): Int = lhs * rhs
  }

  implicit class EqOps[A](x: A)(implicit ev: Multi[A]) {
    def |*|(rhs: A): A = macro DefaultOps.binop[A, A]
  }
}


