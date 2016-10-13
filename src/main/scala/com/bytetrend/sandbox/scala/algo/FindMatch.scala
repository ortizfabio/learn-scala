package com.bytetrend.sandbox.scala.algo

/**
  * Created by db2admin on 6/13/2016.
  */
object FindMatch extends App{

  /**
    * This function finds pattern in a text and
    * returns the index in the text where the pattern was found.
    *
    * @param pattern
    * @param text
    * @return
    */
  def findmatch(pattern:Array[Char], text:Array[Char]): Int ={
    var (i,j) = (0,0)
    //t is the  length of the patter and m the length of the text.
    val (pm,tn) = (pattern.length,text.length)
    for(i <- 0 to (tn-pm)){
      var j = 0
      while(j<pm && text(i+j)==pattern(j))
        j = j + 1
    }
      if(j == pm) return i
    -1
  }

  val pattern = "ien"
  val text = "My mama me ama i my papa tambien X"

  println("pattern "+pattern+" found at "+findmatch(pattern.toCharArray,text.toCharArray))
}
