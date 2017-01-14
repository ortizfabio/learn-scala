package com.bytetrend.sandbox.scala.matching

import java.util.Scanner


object PatternMatch {

  def findMatch(s: String) {
    val zipPattern = "(\\d{5})".r
    val cityStatePattern = "([a-z|A-Z]+), ([A-Z|a-z]{2})".r

    s match {
      case zipPattern(zip) => println(s"Zipcode is: $zip")
      case cityStatePattern(city, state) => println(s"$city, $state")
      case _ => println("did not match a regex")
    }
  }

  /**
    * Enter:
    * 34889
    * or:
    * Denver, CO
    * @param args
    */
  def main(args:Array[String]): Unit ={
    val scanner = new Scanner(System.in)
    println("Enter zip or city, state: ")
    findMatch(scanner.nextLine())
  }
}
