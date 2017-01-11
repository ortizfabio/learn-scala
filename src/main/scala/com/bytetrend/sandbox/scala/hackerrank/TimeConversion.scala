package com.bytetrend.sandbox.scala.hackerrank

import java.time.format.DateTimeFormatter

/**
  * Given a time in -hour AM/PM format, convert it to military (-hour) time.
  * <p>
  * Note: Midnight is  on a -hour clock, and  on a -hour clock. Noon is  on a -hour clock, and  on a -hour clock.
  * <p>
  * Input Format
  * <p>
  * A single string containing a time in -hour clock format (i.e.:  or ), where  and .
  * <p>
  * Output Format
  * <p>
  * Convert and print the given time in -hour format, where .
  * <p>
  * Sample Input
  * <p>
  * 07:05:45PM
  * Sample Output
  * <p>
  * 19:05:45
  */
object TimeConversion {

  def main(args: Array[String]) {
    val sc = new java.util.Scanner (System.in)
    val time = sc.next()
    val inputFormat:DateTimeFormatter = DateTimeFormatter.ofPattern("hh:mm:ssa")
    val  outputFormat : DateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss")
    val date = inputFormat.parse(time)
    println(outputFormat.format(date))
  }
}
