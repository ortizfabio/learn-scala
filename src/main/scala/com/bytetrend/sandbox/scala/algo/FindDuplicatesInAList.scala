package com.bytetrend.sandbox.scala.algo

/**
  * Print duplicates in a list with its count.
  */
object FindDuplicatesInAList extends App {

  import collection.breakOut

  def tooManycall(dup: List[Int]) = {
    println(dup.distinct.map((a: Int) => (a, dup.count((b: Int) => a == b))).filter((pair: (Int, Int)) => {
      pair._2 > 1
    }))
  }

  def acceptable(dup: List[Int]) = {

    //Loops on duplicate a second time to find count
    val list: List[(Int, Int)] = dup.groupBy(identity).collect{ case (x, ys @ List(_, _, _*)) => (x, ys.size) }(breakOut)
    println(list)
  }

  //lengthCompare does not process the whole list it only checks that is greater than 1
  //short circuiting once it is found to be true.
  def dups(dup: List[Int]) = {
    val list: List[(Int, Int)] = dup.groupBy(identity).collect { case (x, ys) if ys.lengthCompare(1) > 0 => (x, ys.size) }(breakOut)
    println(list)
  }

  val dup = List(1, 1, 1, 2, 3, 4, 5, 5, 6, 100, 101, 101, 102)
  dups(dup)
  acceptable(dup)
  tooManycall(dup)
}
