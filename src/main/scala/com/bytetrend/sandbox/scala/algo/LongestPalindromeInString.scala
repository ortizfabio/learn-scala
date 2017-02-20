package com.bytetrend.sandbox.scala.algo

/**
  * Created by db2admin on 2/11/2017.
  */
object LongestPalindromeInString {

  def find(input: Array[Char]): String = {
    var result = ""

    for (left <- 0 until input.length) {
      var str:String = ""
      var right = input.length
      while (left < right) {
        if(left == right){
          str = str+input(left)
        }
        right = right - 1
      }
      if(str.length > 0){

      }
    }
    result
  }

}
