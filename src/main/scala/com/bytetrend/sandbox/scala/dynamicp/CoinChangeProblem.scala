package com.bytetrend.sandbox.scala.dynamicp

/**
  * https://www.youtube.com/watch?v=jaNZ83Q3QGc
  * Write a function to compute the number of combinations
  * that make up that amount.
  * Amount: 5   Coins: 1,2,5
  * 5 = 5
  * 5 = 2 * 2 * 1
  * 5 = 2 * 1 * 1 * 1
  * 5 = 1 * 1 * 1 * 1 * 1
  * Total number of combinations = 4
  *
  * [1 , 1 , 0 , 0 , 0 , 0]
  * [1 , 1 , 1 , 0 , 0 , 0]
  * [1 , 1 , 1 , 1 , 0 , 0]
  * [1 , 1 , 1 , 1 , 1 , 0]
  * [1 , 1 , 1 , 1 , 1 , 1]
  * [1 , 1 , 2 , 1 , 1 , 1]
  * [1 , 1 , 2 , 2 , 1 , 1]
  * [1 , 1 , 2 , 2 , 3 , 1]
  * [1 , 1 , 2 , 2 , 3 , 3]
  * [1 , 1 , 2 , 2 , 3 , 4]
  *
  * Amount: 5   Coins: 2,3,5
  * 5 = 5
  * 5 = 2 + 3
  * Coin 2
  * [1 , 0 , 1 , 0 , 1 , 0]
  * Coin 3
  * [1 , 0 , 1 , 1 , 1 , 1]
  * Coin 5
  * [1 , 0 , 1 , 1 , 1 , 2]
  */
object CoinChangeProblem extends App {

  def coinChange(amount: Int, coins: Array[Int]): Int = {
    val combinations = Array.ofDim[Int](amount + 1)
    combinations(0) = 1
    for (coin <- coins) {
      println(coin)
      for (i <- 1 until combinations.length) {
        if (i >= coin) {
          combinations(i) += combinations(i - coin)
          println(combinations.mkString("[", " , ", "]"))
        }
      }
    }
    combinations(amount)
  }

  var coinArray = Array(1, 2, 5)
  println(coinChange(5, coinArray))

  coinArray = Array(2, 3, 5)
  println(coinChange(5, coinArray))

  coinArray = Array(2, 3, 4)
  println(coinChange(5, coinArray))

}
