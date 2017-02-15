package bm

/**
  * Created by ariwaranosai on 2017/2/15.
  *
  */

import scala.{specialized => sp}

trait TrivalMulti[@sp A] {
  def times(lhs: A, rhs: A): A
}

object TrivalMulti {
  implicit val intEq = new TrivalMulti[Int] {
    @inline
    override def times(lhs: Int, rhs: Int): Int = lhs * rhs
  }

  implicit class EqOps[A](x: A)(implicit ev: TrivalMulti[A]) {
    def |*|(rhs: A): A = ev.times(x, rhs)
  }
}