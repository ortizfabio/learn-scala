package com.bytetrend.sandbox.scala.leetcode

import scala.collection.mutable.ArrayBuffer

/**
  * https://leetcode.com/explore/interview/card/top-interview-questions-medium/109/backtracking/793/
  * Given a string containing digits from 2-9 inclusive, return all possible
  * letter combinations that the number could represent. Return the answer in any order.
  *
  * A mapping of digit to letters (just like on the telephone buttons) is given below.
  * Note that 1 does not map to any letters.
  */
object LetterCombinationOfAPhoneNumber {

  val table = Map[Char, Seq[Char]](
    '2' -> Seq('a', 'b', 'c'),
    '3' -> Seq('d', 'e', 'f'),
    '4' -> Seq('g', 'h', 'i'),
    '5' -> Seq('j', 'k', 'l'),
    '6' -> Seq('m', 'n', 'o'),
    '7' -> Seq('p', 'q', 'r','s'),
    '8' -> Seq('t', 'u', 'v'),
    '9' -> Seq('w', 'x', 'y', 'z'))

  def main(args: Array[String]) = {
    val digits = "239"
    println(versionOne(digits).mkString("[", ",", "]"))
    println(versionTwo(digits).mkString("[", ",", "]"))
  }

  def versionTwo(digits: String): List[String] = {
    if (digits.isEmpty)
      List()
    else {
      val result: Seq[Seq[Char]] = digits.tail.foldLeft(
        table(digits.head).foldLeft(Seq[Seq[Char]]())((acc, x) => acc :+ Seq(x))) {
        (seq: Seq[Seq[Char]], d) => {
          for {x: Seq[Char] <- seq
               c: Char <- table(d)} yield x :+ c
        }
      }

      result.map(x => x.mkString("")).toList
    }
  }

  /**
    * This version calculates the sequences correctly. The only
    * issue with this version is that it clears the result for each
    * loop and copies that to the another sequence.
    *
    * @param digits
    */
  def versionOne(digits: String): List[String] = {
    val result = ArrayBuffer[Seq[Char]]()
    for (i <- digits) {
      result.isEmpty match {
        case true => result ++= table(i).foldLeft(Seq[Seq[Char]]())((acc, x) => acc :+ Seq(x))
        case false => {
          val seq = result.toList
          result.clear()
          for (x <- seq)
            for (c <- table(i))
              result += (x :+ c)
          println(result.size)
        }
      }
    }
    result.map(x => x.mkString("")).toList
  }
}
object LetterCombinationOfAPhoneNumber2 {
  val map: Map[Char, String] = Map(
    '2' -> "abc",
    '3' -> "def",
    '4' -> "ghi",
    '5' -> "jkl",
    '6' -> "mno",
    '7' -> "pqrs",
    '8' -> "tuv",
    '9' -> "wxyz"
  )
  def letterCombinations(digits: String): List[String] = {
    if(digits.isEmpty) return Nil
    def getCombinations(digits: String): List[String] = {
      if(digits.isEmpty) return List("")
      for {
        c <- map(digits.head).toList
        s <- getCombinations(digits.tail)
      } yield c + s
    }
    getCombinations(digits)
  }
  def main(args: Array[String]) = {
    val digits = "239"
    println(letterCombinations(digits).mkString("[", ",", "]"))
  }

}