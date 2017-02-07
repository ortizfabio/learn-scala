package com.bytetrend.sandbox.scala.algo

/**
  * https://en.wikipedia.org/wiki/Power_set
  * https://www.careercup.com/question?id=4862471882932224
  *
  */
object Powerset {
  // Returns the count of ways we can sum  arr[0...coinTypeIndex-1] coins to get sum n
  def findPowerset(solution: List[List[Char]], remaining: List[Char],id:Int): List[List[Char]] = {
    println(solution+" "+id)
    if (remaining.isEmpty) {
     (solution)
    } else {
      val head::tail = remaining
      val sol2 = findPowerset(remaining :: solution , tail,1)
      findPowerset(List(head) :: sol2 , tail,2)
    }
  }


  def main(args: Array[String]): Unit = {
    val str = (new java.util.Scanner(System.in)).nextLine()
    println(findPowerset(List[List[Char]](List[Char]()),str.toCharArray.toList,0).distinct)
  }

}
