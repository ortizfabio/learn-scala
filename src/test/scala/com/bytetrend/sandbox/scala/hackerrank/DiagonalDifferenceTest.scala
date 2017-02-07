package com.bytetrend.sandbox.scala.hackerrank

import org.scalatest.{FunSpec, Matchers}
import com.bytetrend.sandbox.scala.hackerrank.DiagonalDifference.calc

class DiagonalDifferenceTest extends FunSpec with Matchers {
  describe("A diagonal difference implementation of Hackerrank contest ") {
    it("Calculates difference between diagonals of matrix {11 2 4}, {4 5 6},{10 8 -12} = 15") {
      val matrix:List[(Int,Int)] = List((11,0),(0,0),(0,4),(0,0),(5,5),(0,0),(0,10),(0,0),(-12,0))
      calc(matrix) shouldBe 15
    }
  }
}
