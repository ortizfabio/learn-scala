import org.scalatest.FunSuite
import org.scalatest.concurrent.ConductorMethods

import scala.runtime.VolatileIntRef

/**
 * https://issues.scala-lang.org/browse/SI-2424
 */
class ClosureProblemSuite extends FunSuite with ConductorMethods {

  test("closure access to mutable shared state isn't synchronized") {

    @volatile var buf: VolatileIntRef = VolatileIntRef.create(0);

    thread("ping") {

      var ok = 0
      var mistake = 0

      while (true) {
        buf.elem = 42
        if (buf.elem != 42)
          mistake += 1
        else {
          ok += 1
          println("ok: " + ok + " mistake: " + mistake)
        }
      }
    }

    thread("pong") {
      while (true) {
        buf.elem = 0
      }
    }
  }
}