package com.bytetrend.sandbox.scala.challenge


/**
  * <GS>
  * Given two sorted arrays of any length
  * calculate the media of them.
  *
  * ex: A[ 1, 4, 7 9]
  * ex: B[2, 2, 4, 5, 8]
  *
  */
object ComputeMedianOfTwoArray extends App {


  def compute(leftA: Array[Int], rightA: Array[Int]): Int = {
    val isOdd = (leftA.length + rightA.length) % 2 != 0
    val maxLength: Int = (leftA.length + rightA.length) / 2 + 1
//    println(maxLength)
    var last = -1
    var beforeLast = -1
    var leftIndex = -1
    var rightIndex = -1
    for (i <- 0 until maxLength) {
//      println(s"$i $beforeLast $last")
      //If there is more elements in the left array and (no more elements in the right
      // or left is less than the right element
      if (leftIndex < leftA.length - 1
        && ( rightIndex+1 >= rightA.length || leftA(leftIndex+1) < rightA(rightIndex + 1))) {
        leftIndex = leftIndex + 1
        beforeLast = last
        last = leftA(leftIndex)
      } else {
        //There are elements in the right and it is smaller than the left
        rightIndex = rightIndex + 1
        beforeLast = last
        last = rightA(rightIndex)
      }
    }
    if (isOdd)
      last
    else
      (beforeLast + last) / 2
  }

  val r = compute(Array(1, 4, 7, 9), Array(2, 2, 4, 5, 8))
  //1,2,2,4,(4),5,7,8,9
  println(r)
  val s = compute(Array(0,2,8),Array(1,1,4,5,9))
  //0,1,1,(2,4),5,8,9
  println(s)
  val t = compute(Array(1),Array(2,3))
  println(t)
  val u = compute(Array(3),Array(1))
  println(u)
  val w = compute(Array(1),Array())
  println(w)
 }
