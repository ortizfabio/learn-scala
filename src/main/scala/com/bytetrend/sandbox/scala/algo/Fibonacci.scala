
package com.bytetrend.sandbox.scala.algo

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
    if(n == 0)
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


  val fib3: Stream[Int] = 0 #:: 1 #:: (fib3 zip fib3.tail).map {
    t => t._1 + t._2
  }
  val fib4: Stream[Int] = 0 #:: fib4.scanLeft(1)(_ + _)

  def main(args: Array[String]): Unit = {
    println("fib1 " + fib1(10)+ " " + fib1(0) +" " + fib1(2)+" " + fib1(3))
    println("fib2 " + fib2(10)+ " " + fib2(0) +" " + fib2(2)+" " + fib2(3))
    println("fib3 " + fib3(10)+ " " + fib3(0) +" " + fib3(2)+" " + fib3(3))
    println("fib4 " + fib4(10)+ " " + fib4(0) +" " + fib4(2)+" " + fib4(3))
  }

}