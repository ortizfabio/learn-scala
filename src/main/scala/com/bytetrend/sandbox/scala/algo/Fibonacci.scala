
package com.bytetrend.sandbox.scala.algo

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
    var (befPrev, prev, fibonacci) = (1, 1, 1)

      for (i <- 1 until n) {
        fibonacci = befPrev + prev
        befPrev = prev
        prev = fibonacci
      }
      fibonacci
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
    print("fibo "); for(j <- 0 to 10) print(fibo(j)+" "); println
    print("fib1 "); for(j <- 0 to 10) print(fib1(j)+" "); println
    print("fib2 "); for(j <- 0 to 10) print(fib2(j)+" "); println
    print("fib3 "); for(j <- 0 to 10) print(fib3(j)+" "); println
    print("fib4 "); for(j <- 0 to 10) print(fib4(j)+" "); println
    print("fib5 "); for(j <- 0 to 10) print(fib5(j)+" "); println
   }

}