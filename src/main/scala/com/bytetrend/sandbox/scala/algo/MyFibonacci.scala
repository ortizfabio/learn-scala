package com.bytetrend.sandbox.scala.algo

import scala.annotation.tailrec

/**
  * Created by db2admin on 6/3/2016.
  */
object MyFibonacci {

  def fibonacci(n: Int): Int = {
    def fibsum(n: Int, fib: Int): Int = {
       n match {
         case x if n == 0 => 0
         case x if n == 1 => fib
        case _ => fibsum(n - 1, n + fib)
      }
    }
    fibsum(n, 1)
  }

  def fibonacci2(n: Int): Int = {
    @tailrec
    def fibCalc(n: Int, acc: Int): Int = {
      if(n == 0) 0
      else if(n == 1) acc
      else fibCalc(n-1,n+acc)
    }
    fibCalc(n,1)
  }
  def main(args: Array[String]): Unit = {
    (0 to 10).foreach(i => println("For N="+i+" fibonacci= "+fibonacci(i)+" fibonacci2= "+fibonacci2(i)))
  }
}
