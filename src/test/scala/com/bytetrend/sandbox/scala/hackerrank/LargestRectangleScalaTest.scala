package com.bytetrend.sandbox.scala.hackerrank

import com.bytetrend.sandbox.scala.hackerrank.LargestRectangle.maxArea
import org.scalatest.{FunSpec, Matchers}

class LargestRectangleScalaTest extends FunSpec with Matchers {

  //########### Scala ####################################

  describe("A Largest Area") {
    it("Calculates the largest rectangle of 3 3 4 4 5 1 as 15") {
      maxArea(Array[Int](3, 3, 4, 4, 5, 1)) shouldBe 15
    }

     it("Calculates the largest rectangle of 1 2 3 4 5 as 9") {
      maxArea(Array[Int](1, 2, 3, 4, 5)) shouldBe 9
    }

    it("Calculates the largest rectangle of 5 4 3 2 1  as 9") {
      maxArea(Array[Int](5, 4, 3, 2, 1)) shouldBe 9
    }

     it("Calculates the largest rectangle of 5 3 2 1 2 5 as 6") {
      maxArea(Array[Int](5, 3, 2, 1, 2, 5)) shouldBe 6
    }

    it("Calculates the largest rectangle of 1 2 3 5 6 1 as 10") {
      maxArea(Array[Int](1, 2, 3, 5, 6, 1)) shouldBe 10
    }

    it("Calculates the largest rectangle of 10 6320 6020 6098 1332 7263 672 9472 28338 3401 9494 as 28338") {
      maxArea(Array[Int](10, 6320, 6020, 6098, 1332, 7263, 672, 9472, 28338, 3401, 9494)) shouldBe 28338
    }
  }

}
