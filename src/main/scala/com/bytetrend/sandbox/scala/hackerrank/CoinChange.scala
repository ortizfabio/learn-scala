package com.bytetrend.sandbox.scala.hackerrank

/**
  * Created by db2admin on 1/12/2017.
  */
object CoinChange extends App{

  var counter = 0;

  def findChange(arr: Array[Int], coinTypeIndex: Int, amount: Int, str: String): Int = {
    counter = counter + 1
    println(counter + " " + coinTypeIndex +
      (if (coinTypeIndex > -1) " coin [" + arr(coinTypeIndex) + "]" else "")
      + " a=" + amount + " " + str)
    if (amount == 0) {
      1
    } else if (coinTypeIndex < 0) {
      0
    } else if (amount < 0) {
      0
    } else {
      val excl = findChange(arr, coinTypeIndex - 1, amount, str)
      val incl = findChange(arr, coinTypeIndex, amount - arr(coinTypeIndex), str + " " + arr(coinTypeIndex))
      println(s"$counter incl=$incl excl=$excl ")
      incl + excl
    }
  }

  val arr = Array(1, 5, 10, 25)
  println(s"$counter result is " + findChange(arr, arr.length - 1, 16, " "))

}
