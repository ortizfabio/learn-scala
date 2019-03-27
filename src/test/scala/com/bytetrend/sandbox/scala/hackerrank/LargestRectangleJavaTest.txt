package com.bytetrend.sandbox.scala.hackerrank


import com.bytetrend.sandbox.java.hackerrank.LargestRectangle.{largestRectangleArea, largestRectangleArea2}
import org.scalatest.{FunSpec, Matchers}

class LargestRectangleJavaTest extends FunSpec with Matchers {

  //########### JAVA 1 ####################################
  describe("A Largest Rectangle") {

    it("Calculates the largest rectangle of 3 3 4 4 5 1 as 15") {
      largestRectangleArea(Array[Int](3, 3, 4, 4, 5, 1)) shouldBe 15
    }
    it("Calculates the largest rectangle of 1 2 3 4 5 as 9") {
      largestRectangleArea(Array[Int](1, 2, 3, 4, 5)) shouldBe 9
    }

    it("Calculates the largest rectangle of 5 4 3 2 1  as 9") {
      largestRectangleArea(Array[Int](5, 4, 3, 2, 1)) shouldBe 9
    }
    it("Calculates the largest rectangle of 5 3 2 1 2 5 as 6") {
      largestRectangleArea(Array[Int](5, 3, 2, 1, 2, 5)) shouldBe 6
    }

    it("Calculates the largest rectangle of 1 2 3 5 6 1 as 10") {
      largestRectangleArea(Array[Int](1, 2, 3, 5, 6, 1)) shouldBe 10
    }
    it("Calculates the largest rectangle of 10 6320 6020 6098 1332 7263 672 9472 28338 3401 9494 as 28338") {
      largestRectangleArea(Array[Int](10, 6320, 6020, 6098, 1332, 7263, 672, 9472, 28338, 3401, 9494)) shouldBe 28338
    }

  }


  //########### JAVA 2 ####################################

  describe("A Largest Rectangle2") {
    it("Calculates the largest rectangle of 3 3 4 4 5 1 as 15") {
      largestRectangleArea2(Array[Int](3, 3, 4, 4, 5, 1)) shouldBe 15
    }

    it("Calculates the largest rectangle of 1 2 3 4 5 as 9") {
      largestRectangleArea2(Array[Int](1, 2, 3, 4, 5)) shouldBe 9
    }

    it("Calculates the largest rectangle of 5 4 3 2 1  as 9") {
      largestRectangleArea2(Array[Int](5, 4, 3, 2, 1)) shouldBe 9
    }

    it("Calculates the largest rectangle of 5 3 2 1 2 5 as 6") {
      largestRectangleArea2(Array[Int](5, 3, 2, 1, 2, 5)) shouldBe 6
    }

    it("Calculates the largest rectangle of 1 2 3 5 6 1 as 10") {
      largestRectangleArea2(Array[Int](1, 2, 3, 5, 6, 1)) shouldBe 10
    }

    it("Calculates the largest rectangle of 10 6320 6020 6098 1332 7263 672 9472 28338 3401 9494 as 28338") {
      largestRectangleArea2(Array[Int](10, 6320, 6020, 6098, 1332, 7263, 672, 9472, 28338, 3401, 9494)) shouldBe 28338
    }

  }

}
