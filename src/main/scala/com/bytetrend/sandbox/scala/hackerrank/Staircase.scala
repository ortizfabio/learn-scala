package com.bytetrend.sandbox.scala.hackerrank


object Staircase {

  /**
    * https://www.hackerrank.com/challenges/staircase
    * Consider a staircase of size n = 4:
    *
    *    #
    *   ##
    *  ###
    * ####
    * Observe that its base and height are both equal to n, and the image is drawn using # symbols and spaces. The last line is not preceded by any spaces.
    * Write a program that prints a staircase of size n.
    * *
    * Input Format
    * *
    * A single integer, n, denoting the size of the staircase.
    * *
    * Output Format
    * *
    * Print a staircase of size n using # symbols and spaces.
    * *
    * Note: The last line must have 0 spaces in it.
    * *
    * Sample Input
    * *
    * 6
    *
    * @param n
    * @return
    */
  def staircase(n: Int): Seq[String] = {
    for {
      x <- 1 to n
    } yield " " * (n - x) + "#" * x
  }

  def main(args: Array[String]) {
    val sc = new java.util.Scanner(System.in);
    val n = sc.nextInt();
    println(staircase(n).mkString("\n"))
  }

}