package com.bytetrend.sandbox.scala.hackerrank


/**
  * John Watson performs an operation called a right circular rotation on an array of integers, [a0,a1,...an-1].
  * After performing one right circular rotation operation, the array is transformed from [a0,a1,...an-1] to [an-1,a0,...an-2].
  * *
  * Watson performs this operation k times. To test Sherlock's ability to identify the current
  * element at a particular position in the rotated array, Watson asks q queries, where each query
  * consists of a single integer, , for which you must print the element at index m in the rotated
  * array (i.e., the value of am).
  * *
  * Input Format
  * *
  * The first line contains 3 space-separated integers, n, k, and q, respectively.
  * The second line contains  space-separated integers, where each integer i
  * describes array element ai (where 0 <= i < n).
  * Each of the  subsequent lines contains a single integer denoting m.
  *
  * https://www.hackerrank.com/challenges/circular-array-rotation
  * For each query, print the value of the element at index m of the rotated array on a new line.
  * Sample Input
  * 3 2 3
  * 1 2 3
  * 0
  * 1
  * 2
  *
  * Sample Output
  * 2
  * 3
  * 1
  */
object CircularArrayRotation {

  /**
    * Given an index in the rotated array print the
    *
    * Rotate by 2
    * |9|4|6| -> *2 -> |4|6|9| value at index 0 now is 4
    * 0 - 2 + 3 =  1 what was at index 1 orginally is at index 0 after rotation.
    * Rotate by 5
    * |9|4|6| -> *5 -> |4|6|9| value at index 0 now is 4 same as before 5 % 3 = 2
    * 0 - (2%3) + 3 = 1 same as before but the rotation is fixed to be less than n
    *
    * @param mIndex  an index in the rotated array
    * @param rotationCount how many time the array has been rotated
    * @param arrayLength the length of the array
    * @return the index in the non-rotated array.
    */
  def findValueAtIndex(mIndex:Int,rotationCount:Int,arrayLength:Int) =
  //if original array is {1 2 3}
  // If rotated 2 times results in {2 3 1} querying for index 0
  //should return index 1 in original which has value of 2.
  // Calculating formula (0 - ( 2 % 3) + 3) % 3 = 1 or a(1)=2
  // If rotated 4 times results in (3 1 2} querying for index 0
  // should return index 2 in original witch has value of 3

  //The index will always be from 0 to n the rotationCount can be > n
  //Therefore if it is greater than n it should be reset to be from 0 to n - 1
  //because rotating more than n times it is the same rotation % n
  //When the rotation is less than n t
    (mIndex - (rotationCount % arrayLength) + arrayLength) % arrayLength

  def main(args: Array[String]): Unit = {
    val in = new java.util.Scanner(System.in)
    // Length of array of integers
    val arrayLength = in.nextInt
    //How many times the original array will be rotated.
    val rotationCount = in.nextInt
    //How many index/value queries will be performed.
    val queries = in.nextInt
    val array = new Array[Int](arrayLength)
    //Iniitialize values in array by reading input
    for (i <- 0 until arrayLength) array(i) = in.nextInt
    //Read array of indexes to be queried on rotated array.
    val indexes = (0 until queries).collect{case _ :Int => in.nextInt}

    //For each index to be queried print the corresponding value in the rotated array.
    indexes.foreach(m => println(array(findValueAtIndex(m,rotationCount,arrayLength))))


  }
}
