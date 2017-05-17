package com.bytetrend.sandbox.scala.hackerrank

/**
  * Input format: The first line contains the number of test cases T. T test cases follow,
  * each consisting of two lines. The first line of each test case contains a number N. The
  * next line contains N integers denoting the predicted price of WOT shares for the
  * next N minutes.
  *
  * Output Format:
  * Output T lines each containing the maximum profit which can be obtained for the
  * corresponding test case.
  *
  * Sample Input:
  * 3
  * 3
  * 5 3 2
  * 3
  * 1 2 100
  * 4
  * 1 3 1 2
  *
  * Sample Output:
  * 0
  * 197
  * 3
  * Explanation:
  * For the first case you can't buy any shares because the price is going down.
  * For the second case you can buy one share on the first two minutes and sell both of them
  * on the third minute.
  *
  * For the third case you can buy one share on first minute sell one on second minute,
  * buy one share on third minute, and sell one share on fourth minute.
  *
  */
object Stock {

  def main(args: Array[String]) {
    //Enter your code here. Read input from STDIN. Print output to STDOUT
    val sc = new java.util.Scanner(System.in)
    val totalTest = sc.nextInt()
    for(i <- 0 until totalTest){
      val ticks = sc.nextInt()
      var a: Array[Int] = Array.ofDim[Int](ticks)
      for(j <- 0 until ticks){
        a(j) = sc.nextInt
        println(calculate(a))
      }
    }

  }

  def calculate(a: Seq[Int]): Int = {
    var shares = 0
    for(i <- 0 until a.length - 1){
      if(a(i) < a(i+1))
        shares = shares + 1
    }
    shares * a(a.length -1)
  }
}