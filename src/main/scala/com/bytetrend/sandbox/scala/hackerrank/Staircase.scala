package com.bytetrend.sandbox.scala.hackerrank


object Staircase  {

  def staircase(n: Int): Seq[String] = {
   for {
      x <- 1 to n
    } yield " " * (n - x) + "#" * x
  }
  def main(args: Array[String]) {
    val sc = new java.util.Scanner (System.in);
    var n = sc.nextInt();
    println(staircase(n).mkString("\n"))
  }

}