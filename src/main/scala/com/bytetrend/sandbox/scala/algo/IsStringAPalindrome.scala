package com.bytetrend.sandbox.scala.algo

/**
  * Palindrome Permutation: Given a string, write a function to check if it is a permutation of
  * a palindrome. A palindrome is a word or phrase that is the same forwards and backwards. A
  * permutation is a rearrangement of letters. The palindrome does not need to be limited to just
  * dictionary words.
  * EXAMPLE
  * Input: Tact Coa
  * Output: True (permutations: "taco cat'; "atc o eta·; etc.)
  */
object IsStringAPalindrome extends App {

  def isStringAPalindrome(phrase: String): Boolean = {

    val oddCount = getOddCount(buildCharFrequency(phrase))
    //Empty string are not palindrome
    if(phrase.trim.length == 0 )
      return false
    if (phrase.length % 2 == 0 && oddCount == 0)
      true
    else if (phrase.length % 2 == 1 && oddCount == 1)
      true
    else
      false

  }

  /**
    * This is the first step to count each character between a and z
    * palindrom is assumed to be lower case only.
    *
    * @param s
    * @return
    */
  def buildCharFrequency(s: String): Array[Int] = {
    val table: Array[Int] = Array.ofDim[Int](128)
    for (c <- s) {
      if ('a' <= c && c <= 'z')
        table(c.toInt - 'a'.toInt) += 1
    }
    table
  }

  /**
    * this returns the number of letters with an odd count.
    * It is used to determine if a palindrome is possible.
    * Only when there none or only one the string can be a palindrom.
    * @param table
    * @return
    */
  def getOddCount(table: Array[Int]): Int = {
    table.fold(0)((l: Int, r: Int) => l + (r % 2))
  }

  var phrase = " rr ssklm n"
  println(s"Is palindrome $phrase =  ${isStringAPalindrome(phrase)}")

  phrase = " a "
  println(s"Is palindrome $phrase =  ${isStringAPalindrome(phrase)}")

  phrase = "a ab cc  "
  println(s"Is palindrome $phrase =  ${isStringAPalindrome(phrase)}")

  phrase = "aacc"
  println(s"Is palindrome $phrase =  ${isStringAPalindrome(phrase)}")

  phrase = "aaacc"
  println(s"Is palindrome $phrase =  ${isStringAPalindrome(phrase)}")

  phrase = "a a b c c"
  println(s"Is palindrome $phrase =  ${isStringAPalindrome(phrase)}")

  phrase = ""
  println(s"Is palindrome $phrase =  ${isStringAPalindrome(phrase)}")

}
