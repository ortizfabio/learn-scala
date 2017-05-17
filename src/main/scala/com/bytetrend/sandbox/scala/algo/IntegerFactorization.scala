package com.bytetrend.sandbox.scala.algo

/**
  * Created by db2admin on 17/05/12.
  */
object IntegerFactorization extends App{

  def findFactors(num:Int) ={
    var i = 2
    val max = Math.sqrt(num)
    while(i <= max){
      if( num % i == 0){
        println(i)
        if( i != (num / i)){
           println(num/i)
        }
      }
      i = i + 1
    }
  }
  println("##### 100 ")
  findFactors(100)
  println("##### 15 ")
  findFactors(15)
  println("##### 85 ")
  findFactors(85)
}
