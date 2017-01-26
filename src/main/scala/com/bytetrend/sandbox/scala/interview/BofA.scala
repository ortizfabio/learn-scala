package com.bytetrend.sandbox.scala.interview

object BofA {

  def findDuplicate(array: Array[Int]): Int ={
    array.toSeq.flatMap(i => array.tail.map(j => (i,j))).filter(x => x._1 ==x._2).head._1
  }

  def findDuplicate2(array: Array[Int]):Int ={
    array.sum - array.distinct.sum
  }

  def main(args:Array[String])={
    val array = Array(4,2,4,5,3,1)
    println(findDuplicate(array))
    println(findDuplicate2(array))
  }

}
