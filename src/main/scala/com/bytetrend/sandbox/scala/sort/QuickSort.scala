package com.bytetrend.sandbox.scala.sort

import scala.annotation.tailrec



object Recursive extends App {

  def quicksort[T](xs: List[T])(gt: (T, T) => Boolean): List[T] = {

    @annotation.tailrec
    def qsort(todo: List[List[T]], done: List[T]): List[T] = {
//      println(todo.mkString(",") + " - " + done.mkString(","))
      todo match {
        case Nil => done
        case xshead :: xstail => xshead match {
          case Nil => qsort(xstail, done)
          case pivot :: tail =>
            //Compare list against pivot return > on left and < on right
            //This is so that ordering of elements on done is done
            //by pre-pending the largest number in the list and so on.
            val (greater, smaller) = (tail partition (gt(_, pivot)))
            //Check if pivot is the greater that is when greater list is empty
            if (greater.isEmpty) {
              //Since pivot is the next greater lets added to the done list
              if (smaller.isEmpty)
                qsort(xstail, pivot :: done)
              else
                qsort(smaller :: xstail, pivot :: done)
            } else {
              //keep sorting until we find the next greater
              qsort(greater :: List(pivot) :: smaller :: xstail, done)
            }
        }
      }
    }

    qsort(List(xs), Nil)
  }

  val sc = new java.util.Scanner(System.in)
  val array = sc.nextLine().split(" ").map(_.toInt)

  println(array.mkString("[", ",", "]"))


  println(quicksort(array.toList)(Ordering[Int].gt).mkString(","))

}

object NonRecursive extends App {
  def qsrt[T](xs: List[T])(implicit lt: (T, T) => Boolean): List[T] = {
    xs match {
      case Nil => Nil
      case pivot :: tail =>
        val (smaller, larger) = tail.partition(lt(_, pivot))
        qsrt(smaller) ::: pivot :: qsrt(larger)
    }
  }

  val sc = new java.util.Scanner(System.in)
  val array = sc.nextLine().split(" ").map(_.toInt)

  println(array.mkString("[", ",", "]"))


  println(qsrt(array.toList)(Ordering[Int].lt).mkString(","))


}

/**
  * http://100timesover.blogspot.gr/2012/09/quicksort-rewritten-in-tail-recursive.html
  */
object QuickSort extends App {

  def quicksort(xs: Array[Int]) {
    def swap(i: Int, j: Int) {
      val t = xs(i); xs(i) = xs(j); xs(j) = t
    }
    def sort1(l: Int, r: Int) {
      val pivot = xs((l + r) / 2)
      var i = l
      var j = r
      while (i <= j) {
        while (xs(i) < pivot) i += 1
        while (xs(j) > pivot) j -= 1
        if (i <= j) {
          swap(i, j)
          i += 1
          j -= 1
        }
      }
      if (l < j) sort1(l, j)
      if (i < r) sort1(i, r)
    }
    sort1(0, xs.length - 1)
  }

  val sc = new java.util.Scanner(System.in)
  val array = sc.nextLine().split(" ").map(_.toInt)

  println(array.mkString("[", ",", "]"))
  quicksort(array)

  println(array.mkString(","))

}

