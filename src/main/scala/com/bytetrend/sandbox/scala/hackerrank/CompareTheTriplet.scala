package com.bytetrend.sandbox.scala.hackerrank

import java.io._

import scala.collection.Seq
import scala.io._

/**
  * https://www.hackerrank.com/challenges/compare-the-triplets/problem
  * Input (stdin)
  * 5 6 7
  * 3 6 10
  * Expected Output
  * 1 1
  */
object CompareTheTriplet {

  // Complete the compareTriplets function below.
  def compareTriplets(a: Array[Int], b: Array[Int]): Array[Int] = {
    assert(a.length == b.length)
    val xs = for {
      (x, i) <- a.view.force.zipWithIndex
    } yield {
      if (x > b(i)) (1, 0) else if (b(i) > x) (0, 1) else (0, 0)
    }
    xs.foldLeft((0, 0)) {
      case (acu, y) => (acu._1 + y._1, acu._2 + y._2)
    } match {
      case (x: Int, y: Int) => Array(x, y)
    }

  }

  def main(args: Array[String]) {
    val printWriter = new PrintWriter(System.out)

    val a = StdIn.readLine.replaceAll("\\s+$", "").split(" ").map(_.trim.toInt)

    val b = StdIn.readLine.replaceAll("\\s+$", "").split(" ").map(_.trim.toInt)
    val result = compareTriplets(a, b)

    printWriter.println(result.mkString(" "))

    printWriter.close()
  }

}
