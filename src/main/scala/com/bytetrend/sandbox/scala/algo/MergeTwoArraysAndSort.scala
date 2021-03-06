package com.bytetrend.sandbox.scala.algo


object MergeTwoArraysAndSort {

  def mergeAndSort(arrayOne: Array[Int], arrayTwo: Array[Int]): Array[Int] = {
    val result = (arrayOne ++ arrayTwo).sorted
    result
  }

  def main(args: Array[String]): Unit = {
    import com.bytetrend.sandbox.scala.sort.MergeSort.merge
    val arrayOne = Array[Int](3, 4, 9, 0, 1, 2, 4)
    val arrayTwo = Array[Int](5, 4, 1, 0, 9, 8)


    val result = mergeAndSort(arrayOne, arrayTwo)
    println(s"array length=${result.length} [ ${result.deep.mkString(", ")}]")
    val result2 = merge(arrayOne.toList, arrayTwo.toList)
    println(s"array length=${result2.length} [ ${result2.mkString(", ")}]")
  }

}
