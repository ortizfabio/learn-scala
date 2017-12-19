package com.bytetrend.sandbox.scala.algo

/**
  * Permutate strings.
  */
object StringPermutation extends App{

  def permutate(str:String)={

    def permutate(current:String, prefix:String):Unit={
      println(s"$current - $prefix")
      if(current isEmpty)
        println(prefix)
      else
        for(i <- 0 until (current.length)){
          val newCurrent = current.substring(0,i) + current.substring(i+1)
          val char = current.charAt(i)
          permutate(newCurrent, prefix + char )
        }
    }
    permutate(str,"")
  }

  permutate("LOC")
}
