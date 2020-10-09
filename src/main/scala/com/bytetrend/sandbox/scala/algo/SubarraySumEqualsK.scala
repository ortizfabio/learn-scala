package com.bytetrend.sandbox.scala.algo

import scala.collection.mutable

/**
  * https://leetcode.com/problems/subarray-sum-equals-k/
  * Solution explanation https://www.youtube.com/watch?v=bqN9yB0vF08
  */
object SubarraySumEqualsK {

  def main(args: Array[String]) = {
    println(s"count is: ${subArraySum(Array(3, 4, 7, 2, -3, 1, 4, 2), 7)} expected 4")
    println(s"count is: ${subArraySum(Array(0, 0, 0, 0, 0, 0, 0, 0, 0, 0), 0)} expected 55")
  }

  def subArraySum(nums: Array[Int], k: Int): Int = {
    val map = mutable.Map[Int, Int](0 -> 1)
    var count = 0
    var sum = 0
    for (n <- nums) {
      sum += n
      val diff = sum - k
      map.get(diff) match {
        case Some(x) =>count += x
        case _ =>
      }
      map.get(sum) match {
        case Some(y) => map += (sum -> (y + 1))
        case None => map += (sum -> 1)
      }
    }
    count

  }
}
