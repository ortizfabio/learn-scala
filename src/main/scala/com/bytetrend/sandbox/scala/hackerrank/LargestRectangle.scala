package com.bytetrend.sandbox.scala.hackerrank

import scala.collection.mutable


/**
  * https://www.hackerrank.com/challenges/largest-rectangle
  * Input:
  * 8
  * 1 2 3 4 5 3 3 2
  * Output:
  * 15
  * Input:
  * 5
  * 1 2 3 4 5
  * Output:
  * 9
  * Input:
  * 5
  * 5 4 3 2 1
  * Output:
  * 9
  */
object LargestRectangle {

  import scala.collection.mutable.Stack

  case class PositionHeight(val position: Int, val height: Int)

  def calc(stack: Stack[PositionHeight], index: Int, thisHeight: Int): Int = {
    var area = 0
    //Keeps track of the last position processed.

    while (!stack.isEmpty && stack.top.height > thisHeight) {
      val item = stack.pop()
      val potArea = (index - item.position) * item.height
      area = Math.max(area,potArea)
    }

    if (stack.isEmpty || (stack.top.height != thisHeight ))
      stack.push(PositionHeight(index, thisHeight))
    area
  }

  def maxArea(implicit heights: Array[Int]): Int = {
    var area = 0
    val (stack, _) = heights.foldLeft(((Stack[PositionHeight](), 0))){case((stk,index), h) => {
      area = Math.max(area, calc(stk, index, h))
      ((stk, index + 1))
    }
    }
    //Need to calculate till one pass the end of the array to account for the last bar.
    area = Math.max(area, calc(stack,heights.length, 0))
    area
  }


  def main(args: Array[String]): Unit = {
    val in = new java.util.Scanner(System.in)
    val n = in.nextLine.toInt
    implicit val height: Array[Int] = Array.ofDim(n)
    for (i <- 0 until n) height(i) = in.nextInt
    println(maxArea)
  }

}


/**
  * Don't work version
  * https://www.hackerrank.com/challenges/largest-rectangle
  * *
  * object LargestRectangle1 {
  * *
  * def main(args: Array[String]): Unit = {
  * val in = new java.util.Scanner(System.in)
  * val n = in.nextLine.toInt
  * val height: Array[Int] = Array.ofDim(n)
  * for (i <- 0 until n) height(i) = in.nextInt
  * *
  * var maxSoFar = 0
  * *
  *
  * def findMin(start: Int, end: Int): Int = {
  * var min = height(start)
  * for (i <- start + 1 to end)
  * if (height(i) < min)
  * min = height(i)
  * min
  * }
  * *
  * def calc(start: Int, end: Int) {
  * val thisMax = (end - start + 1) * findMin(start, end)
  * if (thisMax > maxSoFar)
  * maxSoFar = thisMax
  * }
  * *
  * for {i <- 0 until n
  * j <- (n - 1) to 0 by -1
  * if (i <= j)
  * } calc(i, j)
  * println(maxSoFar)
  * }
  * *
  * }
  */
/**
  * Don't work version
  * *
  * object LargestRectangle2 {
  * *
  * def main(args: Array[String]): Unit = {
  * val in = new java.util.Scanner(System.in)
  * val n = in.nextLine.toInt
  * val height: Array[Int] = Array.ofDim(n)
  * for (i <- 0 until n) height(i) = in.nextInt
  * *
  * var maxArea = math.max(height.min * n, height.max * height.count(x => x == height.max))
  * *
  * def calculate(i: Int): Unit = {
  * var j = i + 1
  * val minHeight = height.slice(i, n).min
  * while (j <= n) {
  * val l = height.slice(i, j)
  * val (h, w) = l.foldLeft((l.min, 0))((x, y) => {
  * (
  * if (x._1 < y)
  *x._1
  * else
  * y,
  *x._2 + 1
  * )
  * })
  * val sum = h * w
  * if (sum > maxArea)
  * maxArea = sum
  * if (h <= minHeight)
  * j = n
  * j += 1
  * }
  * if (i + 1 < n)
  * calculate(i + 1)
  * }
  * *
  * calculate(0)
  * println(maxArea)
  * }
  * }
  */