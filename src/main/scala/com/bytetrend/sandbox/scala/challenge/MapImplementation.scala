package com.bytetrend.sandbox.scala.challenge

import scala.annotation.tailrec


object MapImplementation {

  import collection.mutable.ListBuffer

  /**
    * Tail-rec map function to simulate original scala map function.
    * THis is a generic function that converts a list of A into a list of B
    * by applying function f
    *
    * @param list original list
    * @param f    function to be applied.
    * @tparam A type of elements in original list
    * @tparam B type of elements in resulting list.
    * @return list of elements of type B
    */
  def map[A, B](list: List[A], f: A => B): List[B] = {
    @tailrec
    def append(col: List[A], temp: ListBuffer[B]): List[B] = {
      col match {
        case Nil => temp.toList
        case head :: tail => {
          append(tail, temp += f(head))
        }
      }
    }

    append(list, ListBuffer[B]())
  }

  /**
    * recursive version of map
    *
    * @param list original list
    * @param f    function to be applied.
    * @tparam A type of elements in original list
    * @tparam B type of elements in resulting list.
    * @return list of elements of type B
    */
  def map2[A, B](list: List[A], f: A => B): List[B] = {
    list match {
      case Nil => Nil
      case head :: tail => f(head) :: map2(tail,f)
    }

  }
}
