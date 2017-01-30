package com.bytetrend.sandbox.scala.sort

/**
  * Created by db2admin on 1/26/2017.
  */
object QuickSort {

  import collection.mutable.ArrayBuffer

  private def swap(array: ArrayBuffer[Int], left: Int, right: Int) = {
    val t = array(left)
    array(left) = array(right)
    array(right) = t
  }

  private def partition(array: ArrayBuffer[Int], left: Int, right: Int, pivot: Int): Int = {
    var le = left
    var ri = right

    while (le <= ri) {
      while (array(le) < pivot)
        le = le + 1
      while (array(ri) > pivot)
        ri = ri - 1
      if (le <= ri) {
        swap(array, le, ri)
        le = le + 1
        ri = ri - 1
      }
    }
    le
  }

  private def quicksort(array: ArrayBuffer[Int], left: Int, right: Int): Unit = {
    if (left < right) {
      val pivot = array(left + (right - left) / 2)
      val index = partition(array, left, right, pivot)
      println(array.mkString("[", ",", "]") + " " + left + " " + right + " " + pivot + " " + index)
        quicksort(array, left, index - 1)
        quicksort(array, index, right)
    }
  }

  def quicksort(array: ArrayBuffer[Int]): Unit = {
    println(array.mkString("[", ",", "]"))
    quicksort(array, 0, array.length - 1)
    println(array.mkString("[", ",", "]"))
  }

  def main(args: Array[String]) = {
    val sc = new java.util.Scanner(System.in)
    val n: Int = sc.nextLine().toInt
    val array = ArrayBuffer[Int]()
    for (i <- 0 until n)
      array(i) = sc.nextInt
    println(array.mkString("[", ",", "]"))
    quicksort(array)
    println(array.mkString("[", ",", "]"))
  }
}
