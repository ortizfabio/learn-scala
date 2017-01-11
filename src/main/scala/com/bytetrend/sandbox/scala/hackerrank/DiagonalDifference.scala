package com.bytetrend.sandbox.scala.hackerrank


object DiagonalDifference {

  def main(args:Array[String]): Unit ={
    val in  = new java.util.Scanner(System.in)
    val n= in.nextInt

    val list = for {
      i <- 0 until n
      j <- 0 until n
    } yield{
      val z = in.nextInt
      (if(i == j)z else 0,if(i + j == n - 1)z else 0)
    }
    val r = list.reduceLeft((x,y) => (x._1+y._1,x._2+y._2))
    println(math.abs(r._1-r._2))
  }
}