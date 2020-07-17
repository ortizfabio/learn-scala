package com.bytetrend.sandbox.scala

object LongestPalindromeInString {

  import util.control.Breaks._

  def findBiggestPalindromeSubstring(input: String): String = {
    var palindrome = ""
    var tmpStr = ""
    for (i <- 0 until input.length - 1) {
      if (input.charAt(i) == input.charAt(i + 1)) {
        breakable {
          for (
            //The operation will drop the tail of longer sequence either j or k
            (j, k) <- (i to 0 by -1).zip(i + 1 until input.length)
          ) {
            if (input.charAt(j) == input.charAt(k)) {
              tmpStr = input.substring(j, k + 1)
              if (tmpStr.length > palindrome.length)
                palindrome = tmpStr
            } else break
          }
        }
        if (i > 0 && input.charAt(i - 1) == input.charAt(i + 1)) {
          for (
            (j, k) <- (i - 1 to 0 by -1).zip(i + 1 until input.length)
          ) {
            breakable {
              if (input.charAt(j) == input.charAt(k)) {
                tmpStr = input.substring(j, k + 1)
                if (tmpStr.length > palindrome.length)
                  palindrome = tmpStr
              } else break
            }
          }
        }
      }
    }
    palindrome
  }

  def main(args: Array[String]) = {
    val input = "aaabbaaaccdeqjncsdddmmmkkkmmmddd";
    val answer = "dddmmmkkkmmmddd";
    val resp = findBiggestPalindromeSubstring(input)
    println(s"$resp and expected answer $answer match=${answer.equals(resp)}")
  }

}
