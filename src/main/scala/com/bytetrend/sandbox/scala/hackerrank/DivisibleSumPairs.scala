package com.bytetrend.sandbox.scala.hackerrank

import java.io._

/**
  * https://www.hackerrank.com/challenges/divisible-sum-pairs/problem
  *
  * Inputs
  * 6 3
  * 1 3 2 6 1 2
  * output
  * 5
  */
object DivisibleSumPairs {

  // Complete the divisibleSumPairs function below.
  def divisibleSumPairs(n: Int, k: Int, ar: Array[Int]): Int = {
    val xs = for {
      j <- 0 until n
      i <- 0 until j
    } yield
      if ((ar(i) + ar(j)) % k == 0) 1 else 0
    xs.sum
  }

  def main(args: Array[String]) {
    val stdin = scala.io.StdIn

    val printWriter = new PrintWriter(System.out)

    val nk = stdin.readLine.split(" ")

    val n = nk(0).trim.toInt

    val k = nk(1).trim.toInt

    val ar = stdin.readLine.split(" ").map(_.trim.toInt)
    val result = divisibleSumPairs(n, k, ar)

    printWriter.println(result)

    printWriter.close()
  }
}
