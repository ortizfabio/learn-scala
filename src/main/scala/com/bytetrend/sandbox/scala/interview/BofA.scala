package com.bytetrend.sandbox.scala.interview

object BofA {

  /**
    * This function returns the duplicated element of an array of integers.
    * This is done by creating a new collection of pairs of two elements which
    * generates a n x n operation. It then filters the ones for which the elements
    * of the Tuple are the same then returning the first element of the first duplicated pair.
    *
    * This algorithm is slower than the other version but it is able to detect multiple duplicates.
    * The Omega performance is
    *
    * @param array
    * @return
    */
  def findDuplicate1(array: Array[Int]): Int ={
    array.flatMap(i => array.tail.map(j => (i,j))).filter(x => x._1 ==x._2).head._1
  }

  /**
    * The input array contains a list of integers in which one of them is a duplicate.
    * First it obtain the sum of all elements of the array then it substract the sum of
    * the non-repeating elements which returns the value of the duplicate element.
    * The distinct method uses an Set under the covers to remove duplicates.
    * The Omega performance is then 3 time O(n) the first is adding all the elements,
    * the second is adding all the elements to the Set by the distinct operation and the
    * last summing them.
    * This would not work if there are more than one duplicate.
    *
    * @param array
    * @return
    */
  def findDuplicate2(array: Array[Int]):Int ={
    array.sum - array.distinct.sum
  }

  def main(args:Array[String])={
    val array = Array(4,2,4,5,3,1)
    println("Only one duplicate should return 4 == "+findDuplicate1(array))
    println("Only one duplicate should return 4 == "+findDuplicate2(array))
  }

}
