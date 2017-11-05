package com.bytetrend.sandbox.scala.hackerrank

import scala.collection.mutable.ArrayBuffer

/**
  * Steve has a string, S, consisting of n lowercase English alphabetic letters.
  * In one operation, he can delete any pair of adjacent letters with same value.
  * For example, string "aabcc" would become either "aab" or "bcc" after  operation.
  * *
  * Steve wants to reduce S as much as possible. To do this, he will repeat the
  * above operation as many times as it can be performed. Help Steve out by finding
  * and printing 's non-reducible form!
  * *
  * Note: If the final string is empty, print Empty String .
  * *
  * Input Format
  * *
  * A single string, S.
  * *
  * Constraints
  * 1 <= n <= 100
  * *
  * Output Format
  * *
  * If the final string is empty, print Empty String; otherwise, print the
  * final non-reducible string.
  * *
  * Sample Input 0
  * *
  * aaabccddd
  * Sample Output 0
  * *
  * abd
  * Sample Case 0
  * *
  * Steve can perform the following sequence of operations to get the final string:
  * *
  * aaabccddd → abccddd
  * abccddd → abddd
  * abddd → abd
  * Thus, we print abd.
  * *
  * Sample Input 1
  * *
  * baab
  * Sample Output 1
  * *
  * Empty String
  * Explanation 1
  * *
  * Steve can perform the following sequence of operations to get the final string:
  * *
  * baab → bb
  * bb → Empty String
  * Thus, we print Empty String.
  * *
  * Sample Input 2
  * *
  * aa
  * Sample Output 2
  * *
  * Empty String
  * Explanation 2
  * *
  * Steve can perform the following sequence of operations to get the final string:
  * *
  * aa → Empty String
  * Thus, we print Empty String.
  *
  * Input:
  * oagciicgaoyjmahhamjymmwjnnjwmmvpxhpphxpvlikappakilyygvkkvgyymlpfddfplmhiodvvdoihfxpkggkpxfuevvuuvveu
  * Output:
  * Empty String
  */
object SuperReducedString {
  val empty = "Empty String"

  /*
    def reduce(input: Array[Char]):String={
      val output: Array[Char] = new Array[Char](input.length)
      var i = 1
      var j = 0
      while(i <= input.length){
        val before = input(i - 1)
        val current = if(i == input.length){ if(j>0) output(j - 1) else '\u0000'} else input(i)
        if(current != before){
          if(j == 0 || output(j -1) != before) {
            output(j) = before
            j += 1
          }else{
            output(j - 1) = '\u0000'
            j -= 1
          }
        }else{
          if(i == input.length) {
            output(j - 1) = '\u0000'
            j -= 1
          }
          i += 1
        }
        i += 1
      }
      val s = new String(output)
      if(j > 0) s.substring(0,j) else empty
    }
    */
  def reduce(input: Array[Char]): String = {
    val output: Array[Char] = new Array[Char](input.length)
    var i = 0
    var j = 0
    while (i < input.length) {
      val char = input(i)
      //If output array is empty or its last
      //entry is different than char the push
      //to output array and increment j
      if (j == 0 || output(j - 1) != char) {
        output(j) = char
        j += 1
      } else {
        //char and the last entry in output array
        //are the same. It needs to be removed and
        //j reset one back.
        output(j - 1) = '\u0000'
        j -= 1
      }
      //increase counter
      i += 1
    }
    if (j > 0) (new String(output)).substring(0, j) else empty
  }

  def main(args: Array[String]) {
    val sc = new java.util.Scanner(System.in);
    var s = sc.next();
    println(reduce(s.toCharArray))
  }
}
