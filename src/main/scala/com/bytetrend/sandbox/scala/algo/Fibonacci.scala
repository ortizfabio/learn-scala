
package com.bytetrend.sandbox.scala.algo

import scala.annotation.tailrec
import scala.collection.immutable.Stream.consWrapper

object Fibonacci {

  // Fibonacci Series using Dynamic Programming
  // Use Dynamic Programming )
  // We can avoid the repeated calculating number t by storing the
  // Fibonacci numbers calculated so far.
  // Time Complexity: O(n)
  // Extra Space: O(n)
  def fib1(n: Int): Int = {
    var ret = scala.collection.mutable.ListBuffer[Int](0, 1)
    while (ret.length <= n) {
      ret += ret(ret.length - 1) + ret(ret.length - 2)
    }
    ret(n)
  }

  def fibo( n : Int) : Int = {
    @tailrec
    def fib_tail( n: Int, a:Int, b:Int): Int = n match {
      case 0 => a
      case _ => fib_tail( n-1, b, a+b )
    }
    return fib_tail( n, 0, 1)
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
    var (a, b, c) = (0, 1, 0)
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


  def fib3: Stream[Int] = 0 #:: 1 #:: (fib3 zip fib3.tail).map {
    t => t._1 + t._2
  }
  def fib4: Stream[Int] = 0 #:: fib4.scanLeft(1)(_ + _)

  def fib5( n : Int) : Int = n match {
    case 0 | 1 => n
    case _ => fib5( n-1 ) + fib5( n-2 )
  }

  def main(args: Array[String]): Unit = {
    println("fibo " + fibo(10) + " " + fibo(0) + " " + fibo(2) + " " + fibo(3))
    println("fib1 " + fib1(10) + " " + fib1(0) + " " + fib1(2) + " " + fib1(3))
    println("fib2 " + fib2(10) + " " + fib2(0) + " " + fib2(2) + " " + fib2(3))
    println("fib3 " + fib3(10) + " " + fib3(0) + " " + fib3(2) + " " + fib3(3))
    println("fib4 " + fib4(10) + " " + fib4(0) + " " + fib4(2) + " " + fib4(3))
    println("fib5 " + fib5(10) + " " + fib5(0) + " " + fib5(2) + " " + fib5(3))
  }

}