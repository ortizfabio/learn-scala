package com.bytetrend.sandbox.scala.hackerrank

import com.bytetrend.sandbox.scala.algo.PrimeFactors

import scala.collection.mutable.ListBuffer

/**
  * Given a long integer, num, find the number of ways to represent it as a sum
  * of two or more consecutive positive integers. For example
  * if num = 15, then there are three such ways: (1 + 2 + 3 + 4 + 5) = (4 + 5 + 6) = (7 + 8) = 15
  * if num = 2, then there are zero such ways.
  *
  * Complete the consecutive function in the editor below. it has one parameter:
  * a long integer named num. The function must return an integer denoting the
  * number of ways to represent num ass a sum of two or more consecutive
  * positive integers.
  *
  * Input Format:
  * reads a integer denoting num from stdin and passes it to the function.
  *
  * constraints:
  * 1 <= num <= power(10,12)
  * output format:
  * return an integer denoting the number of ways to represent num as a sum of two or more
  * consecutive positive integers.
  *
  * Sample Input:
  * 15
  * Sample Output:
  * 3
  * Explanation:
  * There are 3 ways to calculate num = 15 as a sum of two or more consecutive integers:
  * 1) 1 + 2 + 3 + 4 + 5 = 15
  * 2) 4 + 5 + 6 = 15
  * 3) 7 + 8 = 15
  *
  * Sample Input:
  * 10
  * Sample output:
  * 1
  *
  * Explanation:
  * There is only one way to calculate 10
  * 1) 1 + 2 + 3 + 4 = 10
  *
  * Example 1: In how many ways can 1024 be expressed as the sum of two or more
  * consecutive positive integers?
  *
  * Solution: Since 1024 is a power of two, it has only one odd divisor (namely 1).
  * Therefore, it is not possible to write 1024 as the sum of two or more
  * consecutive positive integers.
  */
object CountConsecutiveSum extends App {
  val series = ListBuffer[Int]()

  def consecutive(num: Long): Int = {

    var count = 0
    if (num > 2) {
      var start = 1
      val ceil = Math.ceil(num / 2)
      while (start <= ceil) {
        var current = start
        var list = current
        series.clear()
        series += current
        while (list <= num) {
          if (list == num) {
            count = count + 1
            println(series.mkString(","))
          }
          current = current + 1
          list = list + current
          series += current
        }
        start = start + 1
      }
    }
    count
  }

  println(s" number is 15 sequences = ${consecutive(15)}\n")
  println(s" number is 600 sequences = ${consecutive(600)}\n")
  println(s" number is 1024 sequences =  ${consecutive(1024)}\n")
}

/**
  *
  * References:
  * https://mathblag.wordpress.com/2011/11/13/sums-of-consecutive-integers/
  * https://math.stackexchange.com/questions/139842/in-how-many-ways-can-a-number-be-expressed-as-a-sum-of-consecutive-numbers
  *
  * Number of ways of writing N as sum of consecutive positive integers
  * is Number of odd factors in that number (except 1).
  *
  * Example 2 : In how many ways can 600 be expressed as the sum of two
  * or more consecutive positive integers?
  * *
  * Solution: By the preceding discussion, this is equal to the number of odd divisors of 600,
  * minus one (to eliminate the trivial solution with one term.) The prime factorization of
  * 600 is
  * *
  * and the number of odd divisors of 600 is (1+1)*(1+2) = 6. Therefore, there are 5 ways
  * to write 600 as the sum of two or more consecutive positive integers.
  */
object CountConsecutiveSum2 extends App with PrimeFactors {
  def consecutive2(num: Long): Int = {
    var count = 0
    if (num > 2) {
      val map = factors(num)
      val list = map.filter(x => x._1 % 2 != 0).map(x => x._2)
      count = list.foldLeft(1)((a, b) => a * (b + 1)) - 1
    }
    count
  }

  println(s" number is 15 sequences = ${consecutive2(15)}")
  println(s" number is 600 sequences = ${consecutive2(600)}")
  println(s" number is 1024 sequences = ${consecutive2(1024)}")
}

