package com.bytetrend.sandbox.scala.hackerrank

import java.io.PrintWriter

import scala.io.StdIn

/**
  * https://www.hackerrank.com/challenges/migratory-birds/problem?h_r=next-challenge&h_v=zen
  *
  * Input:
  * 6
  * 1 4 4 4 5 3
  * Output:
  * 4
  */
object MigratoryBird {

  // Complete the migratoryBirds function below.
  def migratoryBirds(arr: Array[Int]): Int = {
    val stats = arr.foldLeft(Array.fill(5)(0)) {
      case (acc, x) => acc(x - 1) = acc(x - 1) + 1
        acc
    }
    var r = stats.drop(1).foldLeft((0,stats(0),1)){
      case ((maxIndex,max,nextIndex),x) => if(x > max) (nextIndex,x,nextIndex+1) else (maxIndex,max,nextIndex+1)
    }
    r._1+1
  }

  def main(args: Array[String]) {
    val printWriter = new PrintWriter(System.out)

    val arrCount = StdIn.readLine.trim.toInt

    val arr = StdIn.readLine.replaceAll("\\s+$", "").split(" ").map(_.trim.toInt)
    val result = migratoryBirds(arr)

    printWriter.println(result)

    printWriter.close()
  }
}
