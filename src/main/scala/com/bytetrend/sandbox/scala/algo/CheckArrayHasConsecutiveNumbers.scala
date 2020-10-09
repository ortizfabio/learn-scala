package com.bytetrend.sandbox.scala.algo

/**
  * Check if array elements are consecutive
  * Given an unsorted array of numbers, write a function that returns true if array consists of consecutive numbers.
  * <p>
  * Method 1 (Use Sorting)
  * 1) Sort all the elements.
  * 2) Do a linear scan of the sorted array. If the difference between current element and next element is anything other than 1, then return false. If all differences are 1, then return true.
  * <p>
  * Time Complexity: O(nLogn)
  * <p>
  * Method 2 (Use visited array)
  * The idea is to check for following two conditions. If following
  * two conditions are true, then return true.
  * 1) max – min + 1 = n where max is the maximum element in array,
  * min is minimum element in array and n is the number of elements
  * in array.
  * 2) All elements are distinct.
  * <p>
  * To check if all elements are distinct, we can create a visited[]
  * array of size n. We can map the ith element of input array arr[]
  * to visited array by using arr[i] – min as index in visited[].
  * <p>
  * http://www.geeksforgeeks.org/check-if-array-elements-are-consecutive/
  * http://algorithms.tutorialhorizon.com/check-if-array-is-consecutive-integers/
  */
trait CheckArrayHasConsecutiveNumbers {

  /**
    * Method 3 (Mark visited array elements as negative)
    * This method is O(n) time complexity and O(1) extra space, but
    * it changes the original array and it works only if all numbers
    * are positive.
    * <p>
    * We can get the original array by adding an extra
    * step though. It is an extension of method 2 and it has the same two steps.
    * 1) max – min + 1 = n where max is the maximum element in array, min
    * is minimum element in array and n is the number of elements in array.
    * 2) All elements are distinct.
    * <p>
    * In this method, the implementation we modify the input array arr[] to keep
    * track of visited elements. The idea is to traverse the array and for each
    * index i (where 0 <= i < n), make arr[arr[i] – min]] as a negative value.
    * If we see a negative value again then there is repetition.
    * This method modifies the original array by replacing
    * numbers with the indexes according to the distance from
    * the minimum number starting with 1.
    * <p>
    * Then it check in a loop that the abs value store in
    * the arrays is greater than 0
    *
    * @param arr
    * @return
    */
  def withoutAuxArray(arr: Array[Int]): Boolean = {
    val (min, max) = arr.foldLeft(
      (Integer.MAX_VALUE, Integer.MIN_VALUE))(
      (a, b) => (if (b < a._1) b else a._1, if (b > a._2) b else a._2))
    //Sequential integer array must have this condition
    if (arr.length == max - min + 1) {
      for (i <- 0 until arr.length) {
        var index = -1
        if (arr(i) < 0)
          index = -arr(i) - min
        else
          index = arr(i) - min

        if (index < arr.length && arr(index) > 0)
          arr(index) = -arr(index)
        else
          return false
      }
      true
    } else
      false
  }

  /**
    * This method return true if the array contains a sequence
    * of increasing integers by one. It works whether the integers
    * are positive or negative.
    *
    * @param arr
    * @return
    */
  def withAuxArray(arr: Array[Int]): Boolean = {
    val visited = Array.ofDim[Boolean](arr.length)
    val (min, max) = arr.foldLeft((Integer.MAX_VALUE, Integer.MIN_VALUE))((a, b) => (if (b < a._1) b else a._1, if (b > a._2) b else a._2))

    //Sequential integer array must have this condition
    if (arr.length == max - min + 1) {

      for (i <- 0 until arr.length) {
        //If one of the numbers is not in sequence and the range of max-min+1 == array.length
        //is not done it will throw an IndexOutBoundException here.
        val index = arr(i) - min
        if (index >= arr.length || visited(index) != false)
          return false
        visited(index) = true
      }
      // If we have reached till here means , we satisfied all the requirements
      true
    } else
      false
  }

}


object ConsecutiveNumbersTest extends App with CheckArrayHasConsecutiveNumbers {

  println(s"Aux array should be true = ${withAuxArray(Array(21, 24, 22, 26, 23, 25))}")
  println(s"Aux array should be false = ${withAuxArray(Array(21, 24, 22, 26, 28, 25))}")
  println(s"Aux array should be true = ${withAuxArray(Array(-21, -24, -22, -26, -23, -25))}")
  println(s"Aux array should be false = ${withAuxArray(Array(-21, -24, -22, -26, -28, -25))}")
  println(s"Aux array should be true = ${withAuxArray(Array(-4, -2, -1, -3, 0, 1))}")
  println(s"Aux array should be false = ${withAuxArray(Array(-3, -2, 0, 1, 3, 2))}")
  println(s"Aux array should be false = ${withAuxArray(Array(21, 24, 22, 26, 23, 24))}")
  println(s"Aux array should be false = ${withAuxArray(Array(-21, -24, -22, -26, -23, -24))}")

  println(s"In place should be true  = ${withoutAuxArray(Array(11, 10, 12, 14, 13))}")
  println(s"In place should be false  = ${withoutAuxArray(Array(11, 10, 14, 13))}")
  //  println(s"In place should be true  = ${withoutAuxArray(Array(-11, -10, -12, -14, -13))}")
  //  println(s"In place should be false  = ${withoutAuxArray(Array(-11, -10, -14, -13))}")
  //  println(s"In place should be true  = ${withoutAuxArray(Array(-2, -1, 1, 2, 0))}")
  println(s"In place should be false  = ${withoutAuxArray(Array(-3, -1, -1, -2))}")
  println(s"In place should be false  = ${withoutAuxArray(Array(21, 21, 21))}")
  //  println(s"In place should be false  = ${withoutAuxArray(Array(-21, -21, -21))}")


}