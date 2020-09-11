package com.bytetrend.sandbox.scala.algo

import scala.util.Random

/**
  * https://www.careercup.com/question?id=5071329456291840
  */
object FindTheTwoElementsWithSmallestDiference extends App {

  var arr = Seq.fill(10)(Random.nextInt(100)).toArray

  def find(a: Seq[Int]): Option[(Int, Int)] = {
    a.length match {
      case x if (x < 2) => None
      case _ => Some(findSmallestDiff(a.sorted))
    }
  }

  def findSmallestDiff(a: Seq[Int]): (Int, Int) = {
    var sml = (a.head, a.tail.head)
    a.tail.tail.foldLeft(sml._2-sml._1,a.tail.head) { case ((diff,last), n) => {
      if (diff < (n - last))
        (diff, n)
      else {
        sml = (last,n)
        (n - last, n)
      }
    }
    }
    sml
  }

  println(s"for ${arr.sorted.mkString(",")} ${find(arr)}")
  arr = Seq.fill(6)(Random.nextInt(100)).toArray
  println(s"for ${arr.sorted.mkString(",")} ${find(arr)}")
  arr = Seq.fill(2)(Random.nextInt(100)).toArray
  println(s"for ${arr.sorted.mkString(",")} ${find(arr)}")
  arr = Seq.fill(1)(Random.nextInt(100)).toArray
  println(s"for ${arr.sorted.mkString(",")} ${find(arr)}")

}
