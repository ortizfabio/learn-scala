package com.bytetrend.sandbox.scala.hackerrank

import org.scalatest.{FunSpec, Matchers}
import com.bytetrend.sandbox.scala.hackerrank.CircularArrayRotation.findValueAtIndex

class CircularArrayRotationTest  extends FunSpec with Matchers {
  val testArray = List(1,2,3)
  describe("Circular Array Rotation implementation of Hackerrank contest ") {
    it("Given testArray  value at index 2 after rotating the array 2 times should be = 1"){
      testArray(findValueAtIndex(2,2,testArray.length)) shouldBe 1
    }

    it("Given testArray  value at index 2 after rotating the array 5 times should be = 1"){
      testArray(findValueAtIndex(2,5,testArray.length)) shouldBe 1
    }

    it("Given testArray  value at index 2 after rotating the array 3 times should be = 3"){
      testArray(findValueAtIndex(2,3,testArray.length)) shouldBe 3
    }

    it("Given testArray  value at index 0 after rotating the array 1 times should be = 3"){
      testArray(findValueAtIndex(0,1,testArray.length)) shouldBe 3
    }

    it("Given testArray  value at index 0 after rotating the array 6 times should be = 1"){
      testArray(findValueAtIndex(0,6,testArray.length)) shouldBe 1
    }
  }

}
