/**
  * Created by ariwaranosai on 2017/2/15.
  *
  */
import org.scalameter.api._

object benchmark
  extends Bench.LocalTime {
  val sizes: Gen[Int] = Gen.range("size")(3000000, 15000000, 3000000)
  val ranges = for {
    size <- sizes
  } yield 0 until size

  performance of "Multi" in {
    measure method "multi" in {
      using(ranges) in {
        r => {
          import bm.Multi._
          r.reduce(_ |*| _)
        }
      }
    }

    measure method "multinosp" in {
       using(ranges) in {
        r => {
          import bm.MultiNoSp._
          r.reduce(_ |*| _)
        }
      }
    }

    measure method "trivalmulti" in {
       using(ranges) in {
        r => {
          import bm.TrivalMulti._
          r.reduce(_ |*| _)
        }
      }
    }
  }


}

