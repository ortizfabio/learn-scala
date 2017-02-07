package com.bytetrend.sandbox.scala.hackerrank

/**
  * https://www.hackerrank.com/challenges/sherlock-and-array
  *
  * Watson gives Sherlock an array A of length n. Then he asks him to determine
  * if there exists an element in the array such that the sum of the elements
  * on its left is equal to the sum of the elements on its right. If there are
  * no elements to the left/right, then the sum is considered to be zero.
  * Formally, find an i, such that, A0 + A1 + ... + Ai-1 = Ai+1 + Ai+2+...+ An-1.
  *
  * For each test case print YES if there exists an element in the array, such
  * that the sum of the elements on its left is equal to the sum of the elements
  * on its right; otherwise print NO.
  */
object SherlockAndArray {

  /**
    * The number in the middle should be such that multiplying the sum of the numbers
    * before by 2 and adding such number should be equal to the sum of all the numbers.
    *
    * We don't need to iterate over then whole array just up to the number we are looking for.
    *
    * @param a
    * @return
    */
  def calculate(a: Seq[Int]): Option[Int] = {
    val sum = a.reduce(_ + _)
    var currentSum = 0
    for (p <- 0 until a.length) {
      if (2 * currentSum + a(p) == sum) {
        return Some(p)
      }
      currentSum += a(p)
    }
    None
  }

  def main(args: Array[String]) {
    val sc = new java.util.Scanner(System.in)
    val x = sc.nextInt()
    for (i <- 0 until x) {
      val n = sc.nextInt
      var a: Array[Int] = Array.ofDim[Int](n)
      for (j <- 0 until n) {
        a(j) = sc.nextInt
      }
      calculate(a) match {
        case Some(x) => println(s"YES")
        case None => println("NO")
      }
    }
  }


}
