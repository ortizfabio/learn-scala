package com.bytetrend.sandbox.scala.algo

import scala.annotation.tailrec

/**
  * Created by db2admin on 6/3/2016.
  */
object MyFactorial {

  def factorial(n: Int): Int = {
    def factCalc(n: Int, fact: Int): Int = {
       n match {
        case x if n <= 1 => fact
        case _ => factCalc(n - 1, n * fact)
      }
    }
    factCalc(n, 1)
  }

  def factorial2(n: Int): Int = {
    @tailrec
    def factCalc(n: Int, acc: Int): Int = {
      if (n <= 1) acc
      else factCalc(n - 1, n * acc)
    }
    factCalc(n,1)
  }

  def main(args: Array[String]): Unit = {
    (0 to 10).foreach(i => println("For N="+i+" factorial1= "+factorial(i)+" factorial2= "+factorial2(i)))
  }

}
