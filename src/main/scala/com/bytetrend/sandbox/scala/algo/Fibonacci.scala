
package com.bytetrend.sandbox.scala.algo

import scala.collection.immutable.Stream.consWrapper

object Fibonacci {
  def fib1(n: Int): List[Int] = {
    var ret = scala.collection.mutable.ListBuffer[Int](0, 1)
    while (ret.length < n) {
      val temp = ret(ret.length - 1) + ret(ret.length - 2)
      ret += temp
    }
    ret.toList take n
  }

  val fib2: Stream[Int] = 0 #:: 1 #:: (fib2 zip fib2.tail).map { t => t._1 + t._2 }
  val fib3: Stream[Int] = 0 #:: fib3.scanLeft(1)(_ + _)

  def main(args: Array[String]): Unit =
  {
    println("fib1 " + fib1(10))
    println ("fib2 " + fib2(10))
    println ("fib3 " + fib3(10))
  }
}