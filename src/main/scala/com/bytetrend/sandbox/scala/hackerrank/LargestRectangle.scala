package com.bytetrend.sandbox.scala.hackerrank

import scala.collection.mutable


/**
  * https://www.hackerrank.com/challenges/largest-rectangle
  */
object LargestRectangle {

  import scala.collection.mutable.Stack

  case class PositionHeight(val position: Int, val height: Int)

  def calc(stack: Stack[PositionHeight], index: Int, thisHeight: Int): Int = {
    var area = 0
    while (!stack.isEmpty && stack.top.height > thisHeight) {
      val item = stack.pop()
      area = Math.max(area,(index - item.position) * item.height)
    }

    if (stack.isEmpty || (stack.top.height != thisHeight && thisHeight > 0))
      stack.push(PositionHeight(index, index))
    area
  }

  def maxArea(implicit heights: Array[Int]): Int = {
    var area = 0
    val (stack, index) = heights.foldLeft(((Stack[PositionHeight](), 0)))((x, y) => {
      area = Math.max(area, calc(x._1, x._2, heights(x._2)))
      ((x._1, x._2 + 1))
    }
    )
    area = Math.max(area, calc(stack,heights.length, 0))
    area
  }


  def maxAreaSingle(implicit heights: Array[Int]): Int = {
    var area = 0
    val (stack, index) = heights.foldLeft(((Stack[PositionHeight](), 0)))((x, y) => {
      if (x._1.isEmpty ||  y >= x._1.top.height ) {
        x._1.push(PositionHeight(x._2, y))
      }else{

      }
      ((x._1, x._2 + 1))
    })

    while (!stack.isEmpty ) {
      val item = stack.pop()
      area = Math.max(area, (index - item.position) * item.height)
    }

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