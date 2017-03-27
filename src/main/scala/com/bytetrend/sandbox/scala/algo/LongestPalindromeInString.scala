package com.bytetrend.sandbox.scala.algo


object LongestPalindromeInString extends App {

  def find(input: Array[Char]): String = {
    var palindrome = ""
    var tmp = ""
    for (i <- 0 to input.length - 2) {
      // Even palindrome when two consecutive characters are the same.
      if (input(i) == input(i + 1)) {
        var j = i
        var k = i + 1
        while (j >= 0 && k < input.length) {
          if (input(j) == input(k)) {
            tmp = input.mkString.substring(j, k + 1)
            if (tmp.length > palindrome.length)
              palindrome = tmp
            j = j - 1
            k = k + 1
          }else{
            j = -1
          }
        }
      }

      // Odd palindrome when two characters around the current one are the same.
      if (i > 0 && input(i - 1) == input(i + 1)) {
        var j = i - 1
        var k = i + 1
        while (j >= 0 && k < input.length) {
          if (input(j) == input(k)) {
            tmp = input.mkString.substring(j, k + 1)
            if (tmp.length > palindrome.length)
              palindrome = tmp
            j = j - 1
            k = k + 1
          }else{
            j = -1
          }

        }
      }
    }
    palindrome
  }

  val input = "aaabbaaaccdeqjncsdddmmmkkkmmmddd"
  val answer = "dddmmmkkkmmmddd"

  val resp = find(input.toCharArray)
  System.out.println(resp == answer)

}
