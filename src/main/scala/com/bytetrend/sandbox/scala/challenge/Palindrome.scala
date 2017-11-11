package com.bytetrend.sandbox.scala.challenge

/**
  * Palindrome:
  *
  * Anna.
  * Civic.
  * Kayak.
  * Level.
  * Madam.
  * Mom.
  * Noon.
  * Racecar.
  */
object Palindrome extends App {

  /**
    * How do you verify that a sequence of characters form a palindrome.
    *
    */
  def isPalindrome(word: String): Boolean = {
    val size =  'z' - 'a' + 1
    val alphabet = Array.ofDim[Int](size)
    for (c <- word.toLowerCase.toCharArray) {
      alphabet(c - 'a') = alphabet(c - 'a') + 1
    }
    val count = alphabet.count(x => (x != 0 && x % 2 != 0))
    if (word.length % 2 == 0)
      count == 0
    else
      count == 1
  }

  /**
    *
    * Verify that a word is a palindrome
    */
  def palindrome(word: String): Boolean ={
    val lc = word.toLowerCase()
    lc == lc.reverse
  }

  def palindrome2(word:String):Boolean= {
    val n :Int = word.length
    for( i <- 0 to (n/2 - 1) )
      if (word.charAt(i) != word.charAt(n-1-i))
        return false
    return true
  }
}