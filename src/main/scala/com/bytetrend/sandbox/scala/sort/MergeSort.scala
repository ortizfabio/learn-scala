package com.bytetrend.sandbox.scala.sort

import scala.annotation.tailrec


object MergeSort extends App {


  def merge(xs: List[Int], ys: List[Int]): List[Int] = {
    println("merge+" + xs.mkString(",") + " - " + ys.mkString(","))
    val l = (xs, ys) match {
      case (Nil, y1) => ys
      case (x1, Nil) => xs
      case (x1 :: xtail, y1 :: ytail) =>
        if (x1 < y1) x1 :: merge(xtail, ys) else y1 :: merge(xs, ytail)
    }
    println("merge-" + l)
    l
  }

  def msort(xs: List[Int]): List[Int] = {
    val n = xs.length / 2
    println("msort+" + xs + " n=" + n)
    val l = if (n == 0)
      xs
    else {
      val (fst, snd) = xs splitAt n
      merge(msort(fst), msort(snd))
    }
    println("msort-" + l)
    l
  }

  val l1 = List(2, 4)//, 1, 5, 6, 0, 9)
  println(l1)
  println(msort(l1))

}
