package com.bytetrend.sandbox.scala.challenge

/**
  * Write a method that takes two parameters, a single line of
  * text and an integer line length l.
  * The method should print the text with the following wrapping rules:
  * Replace all tilde '~' characters with new lines
  * A word should be wrapped to the next line if the
  * current line's length exceeds l
  * For example
  * given the text:
  * This is some~text and this text needs some wrapping, if you don't~mind
  * And l=15
  *
  * The output would be:
  * This is some
  * text and this
  * text needs some
  * wrapping, if
  * you don't
  * mind
  */
object TextWrapping {

  def printText(line: Array[Char], length: Int) = {
    var currentCount = 0
    var word = ""
    for (i <- 0 until line.length) {
      currentCount = currentCount + 1
      if (line(i) == '~') {
        System.out.println(word)
        word = ""
        currentCount = 0
      } else {
        word = word + line(i)
        if (line(i) == ' ') {
          System.out.print(word)
          word = ""
        }
        if (currentCount == length) {
          currentCount = 0
          System.out.println()
          currentCount = word.length
        }
      }
    }
    System.out.println(word)
  }

  def main(args: Array[String]) {
    //Enter your code here. Read input from STDIN. Print output to STDOUT
    val l = "This is some~text and this text needs some wrapping, if you don't~mind"
    printText(l.toCharArray, 15)
  }

}
