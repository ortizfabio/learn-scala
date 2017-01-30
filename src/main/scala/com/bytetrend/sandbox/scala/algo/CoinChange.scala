package com.bytetrend.sandbox.scala.algo

import com.bytetrend.sandbox.scala.algo.CoinChange.Coin.Coin

import scala.collection.mutable
import scala.reflect.ClassTag

/**
  * http://www.geeksforgeeks.org/dynamic-programming-set-7-coin-change/
  */
object CoinChange {

  var counter = 0;

  // Returns the count of ways we can sum  arr[0...coinTypeIndex-1] coins to get sum n
  def findChange(arr: Array[Int], coinTypeIndex: Int, amount: Int, str: String): Int = {
    counter = counter + 1
    println(counter + " " + coinTypeIndex +
      (if (coinTypeIndex > -1) " coin [" + arr(coinTypeIndex) + "]" else "")
      + " a=" + amount + " " + str)

    if (amount == 0) {
      // If amount is 0 then there is 1 solution (do not include any coin)
      1
    } else if (coinTypeIndex < 0) {
      // If there are no coins and n is greater than 0, then no solution exist
      0
    } else if (amount < 0) {
      // If amount is less than 0 then no solution exists
      0
    }
    // count is sum of solutions (i) including S[coinTypeIndex-1] (ii) excluding S[coinTypeIndex-1]
    val excl = findChange(arr, coinTypeIndex - 1, amount, str)
    val incl = findChange(arr, coinTypeIndex, amount - arr(coinTypeIndex), str + " " + arr(coinTypeIndex))
    println(s"$counter incl=$incl excl=$excl ")
    incl + excl

  }

  object Coin extends Enumeration {
    type Coin = Value
    val PENNY = Value(1, "P")
    val NICKEL = Value(5, "N")
    val DIME = Value(10, "D")
    val QUARTER = Value(25, "Q")
  }

  def findChange4(target: Int): List[List[Int]] = {
    var wallet = scala.collection.mutable.MutableList[List[Int]]()

    for (quarter_count <- 0 to target / 25) {
      val remain_after_quarters = target - quarter_count * 25;
      for (dime_count <- 0 to remain_after_quarters / 10) {
        val remain_after_dimes = remain_after_quarters - dime_count * 10;
        for (nickel_count <- 0 to remain_after_dimes / 5) {
          val penny_count = remain_after_dimes - nickel_count * 5;
          wallet += List(penny_count, nickel_count, dime_count, quarter_count)
        }
      }
    }
    wallet.toList
  }

  //    val combinations = Coin.values.toList.map(x => amount / x.id).map(x => if (x == 0) 1 else x).reduce(_ * _)
  //val fourTuple = List.fill(combinations)(Coin.values.toList.map(_.id))

  def findChange3(amount: Int): List[List[Int]] = {
    var wallet = scala.collection.mutable.MutableList[List[Int]]()

    val coins = Coin.values.toArray.map(_.id)
    for (p <- 0 to (amount / coins(0))) {
      for (n <- 0 to (amount / coins(1))) {
        for (d <- 0 to (amount / coins(2))) {
          for (q <- 0 to (amount / coins(3))) {
            if ((p * coins(0)) + (n * coins(1)) + (d * coins(2)) + (q * coins(3)) == amount)
              wallet += List(p, n, d, q)
          }
        }
      }
    }
    wallet.toList
  }

  def findChange2(amount: Int): Seq[Seq[(Coin, Int)]] = {
    //    implicit val dummyOrdering: Ordering[Seq[(Coin, Int)]] = Ordering.by(_.foldLeft(0)(x => x.)

    val result = mutable.ListBuffer[Seq[(Coin, Int)]]()
    var wallet = List[(Coin, Int)]()

    def calcSum(coinSeq: List[(Coin, Int)], amount: Int) {
      //(p * Coin.PENNY.id) + (n * Coin.NICKEL.id) + (d * Coin.DIME.id) + (q * Coin.QUARTER.id)
      if (coinSeq.map(x => x._1.id * x._2).sum == amount)
        result += wallet
      wallet = List[(Coin, Int)]()
    }

    def addCoins(count: Int, coin: Coin): Unit = {
      wallet = Tuple2(coin, count) :: wallet
    }

    for (p <- 0 to (amount / Coin.PENNY.id)) {
      for (n <- 0 to (amount / Coin.NICKEL.id)) {
        for (d <- 0 to (amount / Coin.DIME.id)) {
          for (q <- 0 to (amount / Coin.QUARTER.id)) {
            addCoins(p, Coin.PENNY); addCoins(n, Coin.NICKEL); addCoins(d, Coin.DIME); addCoins(q, Coin.QUARTER)
            calcSum(wallet, amount)

          }
        }
      }
    }
    println(
      result.map(_.mkString(","))
        .mkString("\n")
    )
    result
  }

  def prod[T](lst: List[T], n: Int) = List.fill(n)(lst).flatten.combinations(n).flatMap(_.permutations)

  //    val l1 = List("a", "b")
  //    val l2 = List(1, 2)
  //    println(prod(l2, l1.size).map(l1.zip(_)).mkString(" "))
  //    val amount = 16
  //    val l1:List[Coin.Value] = Coin.values.toList
  //    val l2: List[Int] = l1.map(c => amount / c.id )
  //    println(prod(l2, l1.size).map(l1.zip(_)).mkString(" "))
  //
  def main(args: Array[String]): Unit = {
    //    val arr = Array(1, 5, 10, 25)
    //   println(s"$counter result is " + findChange(arr, arr.length - 1, 16, " "))
    //println("count is " + findChange4(16).length)
    // println("count is " + findChange3(16).length)
     println("count is " + findChange2(16).length)
  }

  def className[T](v: T)(implicit ev: ClassTag[T]) = ev.toString

}
