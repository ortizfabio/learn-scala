
package com.bytetrend.sandbox.scala.algo

import scala.annotation.tailrec
import scala.collection.immutable.Stream.consWrapper

/**
  * Using Fibonacci as  modern mathematicians do
  * 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377
  * 0, 1, 2, 3, 4, 5,  6,  7,  8,  9, 10,  11,  12,  13
  */
object Fibonacci {

  // Fibonacci Series using Dynamic Programming
  // Use Dynamic Programming )
  // We can avoid the repeated calculating number t by storing the
  // Fibonacci numbers calculated so far.
  // Time Complexity: O(n)
  // Extra Space: O(n)
  def fib1(n: Int): Int = {
    var ret = scala.collection.mutable.ListBuffer[Int](1, 1)
    while (ret.length <= n) {
      ret += ret(ret.length - 1) + ret(ret.length - 2)
    }
    ret(n)
  }

  def fibo(n: Int): Int = {
    @annotation.tailrec
    def fib(n: Int, prev: Int, cur: Int): Int = {
      n match {
        case 0 => prev
        case _ => fib(n - 1, cur, prev + cur)
      }
    }

    fib(n, 1, 1)
  }

  /**
    * Same as fib1 but optimized for space
    * Time Complexity: O(n)
    * Extra Space: O(1)
    *
    * @param n
    * @return
    */
  def fib2(n: Int): Int = {
    var (a, b, c) = (1, 1, 0)
    if (n == 0)
      a
    else {
      for (i <- 1 until n) {
        c = a + b
        a = b
        b = c
      }
      b
    }
  }


  def fib3: Stream[Int] = 1 #:: 1 #:: (fib3 zip fib3.tail).map {
    t => t._1 + t._2
  }
  def fib4: Stream[Int] = 1 #:: fib4.scanLeft(1)(_ + _)

  def fib5( n : Int) : Int = n match {
    case 0 | 1 => 1
    case _ => fib5( n-1 ) + fib5( n-2 )
  }

  def main(args: Array[String]): Unit = {
    println("fibo " + fibo(0) + " " + fibo(1) + " " + fibo(3) + " " + fibo(10))
    println("fib1 " + fib1(0) + " " + fib1(1) + " " + fib1(3) + " " + fib1(10))
    println("fib2 " + fib2(0) + " " + fib2(1) + " " + fib2(3) + " " + fib2(10))
    println("fib3 " + fib3(0) + " " + fib3(1) + " " + fib3(3) + " " + fib3(10))
    println("fib4 " + fib4(0) + " " + fib4(1) + " " + fib4(3) + " " + fib4(10))
    println("fib5 " + fib5(0) + " " + fib5(1) + " " + fib5(3) + " " + fib5(10))
  }

}