package com.bytetrend.sandbox.scala.hackerrank

/**
  * A left rotation operation on an array of size n shifts each of the array's elements 1 unit to the left.
  * For example, if 2 left rotations are performed on array [1.2,3,4,5], then the array would become
  * [3,4,5,1,2].
  * *
  * Given an array of n integers and a number, d, perform d left rotations on the array.
  * Then print the updated array as a single line of space-separated integers.
  * *
  * Input Format
  *
  * The first line contains two space-separated integers denoting the respective values of n
  * (the number of integers) and d (the number of left rotations you must perform).
  * The second line contains n space-separated integers describing the respective
  * elements of the array's initial state.
  * *
  * Constraints
  * 1 <= n <= 10^5^
  * *
  * Output Format
  * *
  * Print a single line of n space-separated integers denoting the final state of the array after performing
  * d left rotations.
  *
  * Sample Input
  * 5 1 2 3 4
  */
object LeftRotation {


  def main(args: Array[String]) {
    val stdin = scala.io.StdIn

    val nd = stdin.readLine.split(" ")

    val n = nd(0).trim.toInt

    val d = nd(1).trim.toInt

    val a = stdin.readLine.split(" ").map(_.trim.toInt)

    val left = d % n


    for(y <- left  until a.length){
      print(a(y) + " ")
    }
    for (x <- 0 until left) {
      print(a(x))
      if (x < left - 1)
        print(" ")
    }
  }

}
