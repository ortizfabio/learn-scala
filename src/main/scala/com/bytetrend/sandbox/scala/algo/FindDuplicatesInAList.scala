package com.bytetrend.sandbox.scala.algo


/**
  * <BoA>
  * Print duplicates in a list with its count.
  */
object FindDuplicatesInAList extends App {

  import collection.breakOut

  def tooManycall(dup: List[Int]) = {
    println("too many calls " + dup.distinct.map((a: Int) => (a, dup.count((b: Int) => a == b))).filter((pair: (Int, Int)) => {
      pair._2 > 1
    }))
  }

  def acceptable(dup: List[Int]) = {
    //Loops on duplicate a second time to find count
    //using manual identity function x:Int = x
    val list: List[(Int, Int)] = dup.groupBy((x: Int) => x).collect { case (x, ys@List(_, _, _*)) => (x, ys.size) }(breakOut)
    println("acceptable " + list)
  }

  //lengthCompare does not process the whole list it only checks that is greater than 1
  //short circuiting once it is found to be true.
  def bestFinder(dup: List[Int]) = {
    val list: List[(Int, Int)] = dup.groupBy(identity).collect { case (x, ys) if ys.lengthCompare(1) > 0 => (x, ys.size) }(breakOut)
    println("bestFinder " + list)
  }

  val dup = Array(1, 1, 1, 2, 3, 4, 5, 5, 6, 100, 101, 101, 102).toList
  bestFinder(dup)
  acceptable(dup)
  tooManycall(dup)
}

object Solution {

  def countDuplicates(numbers: Array[Int]): Int = {
    val l2: Map[Int, Int] = numbers.groupBy(identity).collect { case (x, ys) if ys.lengthCompare(1) > 0 => (x, ys.size) }
    l2.size
  }
  def main(args: Array[String]) {
    println(countDuplicates(Array(1, 1, 1, 2, 2, 3, 4, 9, 3)))
  }


}

object SimpleFind extends App {

  def simpleFindDuplicates(array: Array[Int]): Int = {
    val m = scala.collection.mutable.Map.empty[Int, List[Int]]
    for (k <- array) {
      val v: List[Int] = m.getOrElseUpdate(k, List[Int]())
      m.update(k, k :: v)
    }
    m.retain((k, v) => (v.lengthCompare(1) > 0)).toList.size
  }
  println(simpleFindDuplicates(Array(1, 1, 1, 2, 2, 3, 4, 9, 3)))
}

object SimpleFindWithFilter extends App {

  val dup = Array(1, 1, 1, 2, 3, 4, 5, 5, 6, 100, 101, 101, 102)
  def identity = (x: Int) => x

  def sol(dup: Array[Int]): Int = {
    val map: Map[Int, List[Int]] = dup.toList.groupBy(identity)
    map.filter { case (k, v) => v.lengthCompare(1) > 0 }.size

  }
  println(sol(dup))
}
