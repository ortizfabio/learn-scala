package com.bytetrend.sandbox.scala.algo


/**
  * https://www.careercup.com/question?id=10617665
  * Kaldanes algorithm
  */
object SubarrayLongestSum {

  def main(args: Array[String]) {
    println(s"result ${subArrayLongestSum(Array(-2, -3, 4, -1, -2, 1, 5, -3))}")
  }

  def subArrayLongestSum(a: Array[Int]): Int = {
    var maxSoFar = Int.MinValue
    var maxEndHere = 0

    for(i <- a){
      maxEndHere += i
      if(maxEndHere < i)
        maxEndHere = i
      if(maxSoFar < maxEndHere)
        maxSoFar = maxEndHere
    }
    maxSoFar
  }
}
