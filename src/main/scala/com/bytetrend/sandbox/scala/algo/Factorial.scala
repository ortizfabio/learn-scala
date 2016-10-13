package com.bytetrend.sandbox.scala.algo

object Factorial {

 final def factorial(n:Int):Long ={

    if(n==0)
     1
    else
      (n * factorial(n-1))

  }
 
 def factorial2(n:Int):Int ={
   var result : Int = 1
   if(n > 0)
   for(i:Int <- 1 to n )
     result = n * factorial2(n-1)
    result
 }
  
  def main(args: Array[String]): Unit = {
    println(factorial2(Integer.valueOf(args(0))))
  }
}