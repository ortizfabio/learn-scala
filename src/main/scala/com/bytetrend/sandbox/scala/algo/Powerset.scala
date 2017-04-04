package com.bytetrend.sandbox.scala.algo

import scala.collection.mutable

/**
  * https://en.wikipedia.org/wiki/Power_set
  * https://www.careercup.com/question?id=4862471882932224
  * http://stackoverflow.com/questions/11581175/how-to-generate-the-power-set-of-a-set-in-scala
  */
object Powerset2 {

  def power[A](t: Set[A]): Set[Set[A]] = {
    @annotation.tailrec
    def pwr(t: Set[A], ps: Set[Set[A]]): Set[Set[A]] = {
      println(t.mkString(",")+" -- "+ps.mkString(","))
      if (t.isEmpty) ps
      else pwr(t.tail, ps ++ (ps map (_ + t.head)))
    }

    pwr(t, Set(Set.empty[A])) //Powerset of ∅ is {∅}
  }

  def main(args: Array[String]): Unit = {
    val str = (new java.util.Scanner(System.in)).nextLine()
    println(power(str.toCharArray.toSet) + "solution ")
  }

}
