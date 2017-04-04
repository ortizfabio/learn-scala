package com.bytetrend.sandbox.scala.algo

/**
  * https://www.careercup.com/question?id=9406769
  * Given two unsorted int arrays, find the kth element in the merged, sorted array.
  * example:
  * int[] a= [3 1 7]
  * int[] b = [4 9]
  * k: 3
  * return 4.
  */
object MergeTwoArraysFindK {

  import scala.collection.mutable.PriorityQueue

  def findK(arrayOne: Array[Int], arrayTwo: Array[Int], k: Int): Int = {
   // implicit val ord = Ordering[Int].reverse
    val minHeap = new PriorityQueue[Int] ++= (arrayOne ++ arrayTwo)
    for (i <- 1 to k)
      if (i == k)
        return minHeap.dequeue()
      else
        minHeap.dequeue()
    -1
  }

  def main(args: Array[String]): Unit = {
    val arrayOne = Array[Int](3, 4, 9, 0, 1, 2, 4)
    val arrayTwo = Array[Int](5, 4, 1, 0, 9, 8)
    println(findK(arrayOne, arrayTwo, 4))
  }
}
