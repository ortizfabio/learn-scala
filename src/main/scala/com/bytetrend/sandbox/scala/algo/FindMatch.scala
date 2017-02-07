package com.bytetrend.sandbox.scala.algo


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
    var (ptrnIndex,textIndex) = (0,0)
    //pm is the  length of the pattern and tm the length of the text.
    val (ptrnLen,textLen) = (pattern.length,text.length)
    for(txtIndex <- 0 to (textLen-ptrnLen)){
      var ptrnIndex = 0
      while(ptrnIndex<ptrnLen && text(txtIndex+ptrnIndex)==pattern(ptrnIndex))
        ptrnIndex = ptrnIndex + 1
    }
      if(textLen == ptrnLen) return ptrnIndex
    -1
  }

  val pattern = "ien"
  val text = "My mama me ama i my papa tambien X"

  println("pattern "+pattern+" found at "+findmatch(pattern.toCharArray,text.toCharArray))
}
