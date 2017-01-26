package com.bytetrend.sandbox.scala.interview

import scala.collection.mutable.ListBuffer

/**
  *
  */
object HeadToTail extends App {
/*
Input
3
2
acm
ibm
3
acm
malform
mouse
2
ok
ok

 */

  def solution(args: Array[String]) {
    //Enter your code here. Read input from STDIN. Print output to STDOUT
    val sc = new java.util.Scanner(System.in)
    val test = sc.nextLine.toInt
    for (i <- 0 until test) {
      val list = ListBuffer[(Char, Char)]()
      val count = sc.nextLine.toInt
      for (j <- 0 until count) {
        val word: String = sc.nextLine()
        list += ((word.charAt(0), word.reverse.charAt(0)))
      }
      val ll: ListBuffer[(Char, Char)] = list.sortBy(x => (x._1, x._2))
      if (solve(ll.head, ll.tail))
        println("Ordering is possible.")
      else
        println("The door cannot be opened.")
    }

    def findMatch(x: (Char, Char), list: ListBuffer[(Char, Char)]): Boolean = {
      list match {
        case list if (list.size == 1) => true
        case _ => {
          list -= x
          solve(list.head, list.tail)
        }
      }
    }

    def solve(word: (Char, Char), list: ListBuffer[(Char, Char)]): Boolean = {
      val choices = list.filter((x: (Char, Char)) => x._1 == word._2).iterator
      var result = false
      var x: (Char, Char) = null
      while (choices.hasNext && !result) {
        x = choices.next()
        result = findMatch(x, list)
      }
      result match {
        case false => false
        case true => {
          if (list.size == 1)
            true
          else {
            list -= x
            solve(list.head, list.tail)
          }
        }
      }
    }
  }

  solution(Array[String]())
}
