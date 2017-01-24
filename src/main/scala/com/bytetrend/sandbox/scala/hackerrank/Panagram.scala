package com.bytetrend.sandbox.scala.hackerrank


object Panagram {
  //For those seeing this, toCharArray should always be preferred when
  // possible. toList is painfully slow. Each character primitive becomes a
  // Character object. Each link in the list is an object as well. 2 bytes
  // becomes 12+2+12+2=28 bytes. We can no longer have quick and random
  // access. However, if you are just playing around or writing Hello World,
  // then have at it, but don't expect it to scale well


  def main(args: Array[String]) {
    val stdin = new java.util.Scanner(System.in)

    val s:String = stdin.nextLine()
   val len = s.toUpperCase.toCharArray.distinct.filter(x => (x >= 'A' && x <= 'Z')).length
    if( len == 26)
      println("pangram")
    else
      println("not pangram")
  }
}
