package com.bytetrend.sandbox.scala.sort

/**
  * Created by db2admin on 6/13/2016.
  */
object TestSort {

  def main(args: Array[String]): Unit = {
    val sorter: DualPivotQuicksort = new DualPivotQuicksort()
    val arr: Array[Int] = Array(3, 6, 3, 6, 8, 1, 0, 6, 4, 3)
    println(arr.mkString(" "))
    sorter.sort(arr)
    println(arr.mkString(" "))
  }
}
