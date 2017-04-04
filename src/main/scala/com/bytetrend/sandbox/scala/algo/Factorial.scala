package com.bytetrend.sandbox.scala.algo

import scala.annotation.tailrec

object Factorial {

  final def factorial1(n: Int): Long = {
    if (n == 0)
      1
    else
      (n * factorial1(n - 1))
  }


  def factorial(n: Int): Int = {
    @tailrec
    def factorial(accumulator: Int, n: Int): Int = {
      if (n <= 1)
        return accumulator
      factorial(n * accumulator, n - 1)
    }
    factorial(1,n)
  }
  def factorial5(n:Int ):Int = {
    @tailrec
    def fact(n:Int, prev:Int):Int={
     n  match {
        case 0 => prev
        case _ => fact(n-1, prev * n)
      }
    }
    fact(n,1)
  }

  def factorial2(n: Int): Int = {
    var result: Int = 1
    if (n > 0)
      for (i: Int <- 1 to n)
        result = n * factorial2(n - 1)
    result
  }

  def fact4: Stream[Int] = 1 #:: fact4.scanLeft(1)(_ * _)

  def main(args: Array[String]): Unit = {
    println("factorial1 " + factorial1(10) + " " + factorial1(0) + " " + factorial(2) + " " + factorial1(3))
    println("factorial2 " + factorial2(10) + " " + factorial2(0) + " " + factorial2(2) + " " + factorial2(3))
    println("factorial " + factorial(10) + " " + factorial(0) + " " + factorial(2) + " " + factorial(3))
    println("fact4 " + fact4(10) + " " + fact4(0) + " " + fact4(2) + " " + fact4(3))

  }
}