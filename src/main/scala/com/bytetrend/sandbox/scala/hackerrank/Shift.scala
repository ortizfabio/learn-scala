package com.bytetrend.sandbox.scala.hackerrank

/**
  * We define the following operations on a string:
  * Left Shift: A single circular rotation of the string in which the
  * first character becomes the last character and all other characters
  * are shifted one index to the left. For example abcde becomes bcdea after
  * one left shift and cdeab after two left shifts.
  * Right Shift: A single circular rotation of the string in which the last
  * character becomes the first character and all other characters are shifted
  * one index to the right. For Example abcde becomes eabcd after one right shift
  * and deabc after two right shifts.
  *
  * Complete the function in the editor below. It has three parameters:
  * 1) A string, s, denoting the string to shift.
  * 2) An integer, left, denoting the number of left shifts to perform.
  * 3) An integer, right, denoting the number of right shifts to perfrom.
  *
  * The function must return a string denoting s after performing left left shift
  * operation and right right shift operations.
  *
  * Input Format
  * Locked stub code in the editor reads the following input from stdin and passes
  * it to the function:
  * The first line contains a string, s, denoting the string to shift.
  * The second line contains an integer, left, denoting the number of left shifts to perform.
  * The third line contains an integer, right, denoting the number of right shifts to perform.
  *
  * Output Format
  * return a string denoting s after performing left left shift operations and
  * right right shift operations. This is printed to stdout
  *
  */
object Shift extends App{

  def getShiftedString(s:String, left:Int, right:Int) :String ={
    val total :Int = (left - right) % s.length

    if(total > 0){
      s.substring(total).concat(s.substring(0,total))
    }else if(total < 0){
      val s1 = s.substring(0,s.length -Math.abs(total))
      val s2 = s.substring(s.length - Math.abs(total))
      s2.concat(s1)
    }else s
  }

  println(getShiftedString("abcd",0,3))

}
