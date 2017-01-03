package demo

import scala.annotation.tailrec


object Fibonacci{

  def fibonacci(n:Int):Int={
    @tailrec
    def fib(n: Int, acc: Int):Int={
      if(n <=1){
        acc
      }else fib(n-1,n+acc)
    }
    fib(n,1)
  }
  def main(args: Array[String]): Unit ={

    println(fibonacci(2))
  }
}