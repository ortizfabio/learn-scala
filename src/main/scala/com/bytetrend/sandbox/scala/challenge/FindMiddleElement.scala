package com.bytetrend.sandbox.scala.challenge

/**
  * Find out middle index where sum of both ends are equal.
  * Find the index of the element for which the sum of the elements so far is equal to half the total
  */
object FindMiddleElement {

  def findMiddleElement(array: Array[Int]): Int = {
    val sum = array.sum
    array.zipWithIndex.foldLeft(0)( (currentSum:Int, x :(Int,Int) ) => {
      //Use multiplication by 2 instead of dividing by 2
      // because they are integers and it will match when there is
      //a reminder of 0.5
      if (2 * currentSum == (sum - x._1) )
        return x._2
      else if (x._2 == array.length - 1)
        return -1
      else
        currentSum + x._1
    })
  }

  def main(args: Array[String]) = {
    val array = Array(4, 2, 4, 1, 5, 3, 2)
    println("Should be 3 = " + findMiddleElement(array))
    val array2 = Array(4, 3, 5, 3, 2, 15)
    println("Should be 4 = " + findMiddleElement(array2))
    val array3 = Array(0, 1, 3, 5, 1, 0, 3, 3, 4)
    println("Should be 5 = " + findMiddleElement(array3))

  }

}
