package com.bytetrend.sandbox.scala.sort

import com.bytetrend.sandbox.scala.sort.QuickSort.quicksort

class QuickSortTest extends org.scalatest.FunSpec with org.scalatest.Matchers{

  describe("A pivot sort test"){
    it("Should sort array [15,3,2,1,9,5,7,8,6] in ascending ortder "){
        val array = Array[Int](15,3,2,1,9,5,7,8,6)
        quicksort(array)
      array.mkString("[",",","]") shouldBe "[1,2,3,5,6,7,8,9,15]"
    }
  }

}
