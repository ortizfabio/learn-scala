package com.bytetrend.sandbox.scala.algo

import scala.annotation.tailrec


object BinarySearch {

  def binarySearch1[T](list: Array[T], target: T)(implicit ordering: Ordering[T]): Int = {

    @tailrec
    def bSearch(start: Int, end: Int): Int = {

      if (list.isEmpty || start == end) -1
      else {
        val mid: Int = (start + end) / 2
        if (list(mid) == target)
          mid
        else if (ordering.gt(target,list(mid)))
          bSearch(mid + 1, end)
        else
          bSearch(start, mid)
      }
    }
    bSearch(0,list.length-1)
  }

  def binarySearch2[T](list: Array[T], target: T)(implicit ordering: Ordering[T]): Int = {

    @tailrec
    def bSearch(start: Int, end: Int): Int = {

      if (list.isEmpty || start == end) -1
      else {
        val mid: Int = (start + end) / 2
        list match {
          case array :Array[T] if(array(mid) == target) => mid
          case array :Array[T] if(ordering.lt(target,array(mid))) => bSearch(start,mid-1)
          case _ => bSearch(mid+1,end)
        }
      }
    }
    bSearch(0,list.length)
  }

  def main(args :Array[String]): Unit ={
    implicit val ordering = Ordering[Int]

    println(binarySearch1( Array(1,3,5,7,9,10,20), 3))
    println(binarySearch2( Array(1,3,5,7,9,10,20), 3))

    println(binarySearch1( Array(1,3,5,7,9,10,20), 10))
    println(binarySearch2( Array(1,3,5,7,9,10,20), 10))

    println(binarySearch1( Array(1,3,5,7,9,10,20), 7))
    println(binarySearch2( Array(1,3,5,7,9,10,20), 7))

  }
}