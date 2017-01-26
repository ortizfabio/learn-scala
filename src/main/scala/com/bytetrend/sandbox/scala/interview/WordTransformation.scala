package com.bytetrend.sandbox.scala.interview

/**
  * "Word Transformations"
  * Given two words of equal length that are in a dictionary , write a method to transform one word into another word by changing only one letter at a time. The new word you get in each step must be in the dictionary.
  */
object WordTransformation extends App {
/*
DAMP
LIKE

 */
  import collection.mutable.ArrayBuffer

  val dictionary = List("DAMP", "LAMP", "LIMP", "LIME", "LIKE")
  val alphabeth: Seq[Char] = ('A' to 'Z').map(_.toChar)

  def validate(dictionary: ArrayBuffer[String], a: ArrayBuffer[Char], i: Int, j: Int): Option[String] = {
    a(i) = alphabeth(j)
    dictionary.foreach(
      x => {
        val word = a.mkString
        if (x.equals(word))
          return Some(word)
      })
     None
  }

  def solution(args: Array[String]) {
    //Enter your code here. Read input from STDIN. Print output to STDOUT
    val sc = new java.util.Scanner(System.in)

    while (sc.hasNextLine) {
      var word = sc.nextLine()
      val temp = ArrayBuffer[String](dictionary:_*)
      for (j <- 0 until alphabeth.length)
        for (i <- 0 until word.length) {
          val found = validate(temp,ArrayBuffer[Char](word.toCharArray: _*), i, j)
          found match {
            case Some(x) => {
              temp -= x
              println(x)
            }
            case _ =>
          }
        }
    }
  }

  solution(Array[String]())
}
