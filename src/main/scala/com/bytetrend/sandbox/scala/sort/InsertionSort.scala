package com.bytetrend.sandbox.scala.sort

/**
  * Created by db2admin on 6/13/2016.
  */
class InsertionSort {
  def insertionSort(a: Array[Char]): Unit = {
    def swap(i: Int, j: Int):Unit = {
      var t:Char = a(i)
      a(i)=a(j)
      a(i)=t
    }
    val n = a.length
    for(i:Int <- 1 to n - 1){
      var j:Int = i
      while(j >= 0 && a(j-1) > a(j)){
       swap(j,j-1)
        j -= 1
      }
      println(a.mkString(" "))
    }
  }

  def main(args: Array[String]): Unit = {
     //F G H E A D C B
     val test : Array[Char] = Array('B','C','D','A','E','H','G','F').reverse
     println(test.mkString(" "))
     insertionSort(test)
    println(test.mkString(" "))
  }
}
