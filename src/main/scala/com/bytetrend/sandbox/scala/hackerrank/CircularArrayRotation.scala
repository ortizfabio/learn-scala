package com.bytetrend.sandbox.scala.hackerrank


/**
  * https://www.hackerrank.com/challenges/circular-array-rotation
  * Sample Input
  * 3 2 3
  * 1 2 3
  * 0
  * 1
  * 2
  *
  * Sample Output
  * 2
  * 3
  * 1
  */
object CircularArrayRotation {

  def main(args: Array[String]): Unit = {
    val in = new java.util.Scanner(System.in)
    val n = in.nextInt
    val k = in.nextInt
    val q = in.nextInt
    val a = new Array[Int](n)

    for (i <- 0 until n)
      a(i) = in.nextInt


    (0 until q).foreach(x => {
      val m = in.nextInt
      val index = (m - (k % n) + n) % n
      println(a(index))
    })
  }
}
