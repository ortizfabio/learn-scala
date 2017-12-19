package com.bytetrend.sandbox.scala.geeksforgeeks

/**
  * Inplace rotate square matrix by 90 degrees | Set 1
  *3.5
  * Given an square matrix, turn it by 90 degrees in anti-clockwise direction without using any extra space.
  * *
  * Examples:
  * *
  * Input
  * 1  2  3
  * 4  5  6
  * 7  8  9
  * Output:
  * 3  6  9
  * 2  5  8
  * 1  4  7
  *
  * Input:
  * 1  2  3  4
  * 5  6  7  8
  * 9 10 11 12
  * 13 14 15 16
  * *
  * Output:
  * 4  8 12 16
  * 3  7 11 15
  * 2  6 10 14
  * 1  5  9 13
  * http://www.geeksforgeeks.org/inplace-rotate-square-matrix-by-90-degrees/
  */
object MatrixRotate90Degrees extends App {

  def rotate(mx: Array[Array[Int]]): Unit = {
    var temp1 = 0
    var temp2 = 0
    val last = mx.length - 1
    val limit = mx.length / 2
    for (row <- 0 until limit ) {
      for (col <- row until (last - row)) {
        temp1 = mx(row)(col)
        println(s"m($row)($col) = mx($col)(${last-row})")
        mx(row)(col) = mx(col)(last-row)
        println(s"m($col)(${last-row}) = mx(${last-row})(${last-col})")
        mx(col) (last - row)=  mx(last - row) (last - col)
        println(s"m(${last-row})(${last-col}) = mx(${last-col})($row)")
        mx(last - row)(last - col) = mx(last-col)(row)
        println(s"m(${last-col})($row) = m($row)($col)")
        mx(last - col)(row) = temp1
        mx.foreach(x => println(x.mkString(",")))
        println
      }
    }
  }


  val m1: Array[Array[Int]] = Array(Array(1, 2, 3), Array(4, 5, 6), Array(7, 8, 9))
 // val r1 = Array(Array(2, 6, 9), Array(2, 5, 8), Array(1, 4, 7))
  rotate(m1)


  val m2: Array[Array[Int]] = Array(Array(1, 2, 3,4), Array(5, 6,7,8), Array(9,10,11,12), Array(13,14,15,16))
//  val r2 = Array(Array(2, 6, 9), Array(2, 5, 8), Array(1, 4, 7))
  rotate(m2)


}
