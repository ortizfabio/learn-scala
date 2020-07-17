package com.bytetrend.sandbox.scala.challenge


/*
# Write a function str_to_num data
# You need to convert the input string to a number
# Ex: str_to_num("10") ->  10
# Ex: str_to_num("10.1") -> 10.1
# Ex: str_to_num("-10") -> -10
# Ex: str_to_num("10.10.10") -> Print error or raise Exception
*/

import scala.math.pow

class ParseStringToNumber extends App {

  def strToNum(str: String) = {
    if (str.split(".").length > 1)
      println(s"Invalid String $str")
    else {
      val (n, d) = str.foldLeft((0.0, 0)) { case ((acc, d), c) => {
        if (c == '.') {
          (acc, 1)

        } else {
          (acc * 10 + (c.toInt - 48), if (d > 0) d + 1 else 0)
        }
      }
      }
      println(s"${n / pow(10, d - 1)}")
      //acc / pow(10,acc.toString.length)
    }
  }

  strToNum("105.120")
}
