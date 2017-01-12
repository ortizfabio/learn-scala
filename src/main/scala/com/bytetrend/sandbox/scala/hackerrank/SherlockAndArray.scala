package com.bytetrend.sandbox.scala.hackerrank

/**
  * https://www.hackerrank.com/challenges/sherlock-and-array?h_r=next-challenge&h_v=zen
  */
object SherlockAndArray {

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
