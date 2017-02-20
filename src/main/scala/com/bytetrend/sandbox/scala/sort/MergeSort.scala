package com.bytetrend.sandbox.scala.sort

import scala.annotation.tailrec


object MergeSort extends App {


  def msort1[A](xs: List[A])(implicit less: (A, A) => Boolean): List[A] = {

    def merge(xs1: List[A], xs2: List[A]): List[A] =
      if (xs1.isEmpty) xs2
      else if (xs2.isEmpty) xs1
      else if (less(xs1.head, xs2.head)) xs1.head :: merge(xs1.tail, xs2)
      else xs2.head :: merge(xs1, xs2.tail)

    val n = xs.length / 2
    if (n == 0) xs
    else merge(msort1(xs take n), msort1(xs drop n))
  }


  def merge(xs: List[Int], ys: List[Int]): List[Int] = {
    (xs, ys) match {
      case (Nil, y1) => ys
      case (x1, Nil) => xs
      case (x1 :: xtail, y1 :: ytail) =>
        if (x1 < y1) x1 :: merge(xtail, ys) else y1 :: merge(xs, ytail)
    }
  }

  def msort2(xs: List[Int]): List[Int] = {
    val n = xs.length / 2
    if (n == 0)
      xs
    else {
      val (fst, snd) = xs splitAt n
      merge(msort2(fst), msort2(snd))
    }
  }

  val l1: List[Int] = List(2, 4, 1, 5, 6, 0, 9)
  println(l1)
  println(msort2(l1))
  implicit val less: ((Int, Int) => Boolean) = Ordering[Int].lt
  println(msort1(l1))
}
