package com.bytetrend.sandbox.scala.challenge

object MergeTwoUnsortedArrays extends App {

  val one = Array[Int](4, 2, 7, 10, 33, 0)
  val two = Array[Int](9, 2, 0, 2, 4, 5, 5)

  val merged = (one ++ two).sorted

 println( merged.mkString(","))
 //O(n) n Log(n) where n = A+B
}
