package com.bytetrend.sandbox.scala.interview

/**
  * "Word Transformations"
  * Given two words of equal length that are in a dictionary , write a method to transform
  * one word into another word by changing only one letter at a time.
  * The new word you get in each step must be in the dictionary.
  */
object WordTransformation extends App {
/*
DAMP
LIKE

 */
  import collection.mutable.ArrayBuffer

  val dictionary = Set("DAMP", "LAMP", "LIMP", "LIME", "LIKE")
  val alphabeth: Seq[Char] = ('A' to 'Z').map(_.toChar)

  def validate(a: ArrayBuffer[Char], i: Int, j: Int): Option[String] = {
    a(i) = alphabeth(j)
    if(dictionary(a.mkString))
     Some(a.mkString)
    else
     None
  }

  def solution(args: Array[String]) {
    //Enter your code here. Read input from STDIN. Print output to STDOUT
    val sc = new java.util.Scanner(System.in)

    while (sc.hasNextLine) {
      var word = sc.nextLine()

      for (j <- 0 until alphabeth.length)
        for (i <- 0 until word.length) {
          val found = validate(ArrayBuffer[Char](word.toCharArray: _*), i, j)
          found match {
            case Some(x) => {
              println(x)
            }
            case _ =>
          }
        }
    }
  }

  solution(Array[String]())
}
