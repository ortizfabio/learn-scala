package com.bytetrend.sandbox.scala.hackerrank

import scala.collection.immutable.IndexedSeq

/**
  * Sample Input
  **
  *3
  *11 2 4
  *4 5 6
  *10 8 -12
  *Sample Output
  **
  *15
  *Explanation
  **
  *The primary diagonal is:
  *11
  *   5
  *     -12
  **
  *Sum across the primary diagonal: 11 + 5 - 12 = 4
  **
  *The secondary diagonal is:
  *      4
  *   5
  *10
  *Sum across the secondary diagonal: 4 + 5 + 10 = 19
  * Difference: |4 - 19| = 15
  */
object DiagonalDifference {

  /**
    * Receiving a list of tuples representing pairs from a matrix diagonals
    * Only values that are associated with a diagonal are non zero the main
    * diagonal values are in the first position of the pair the secondary
    * diagonal values are in the second position of the pair. We only need
    * to sum the first values with the first values and the same for the second
    * values.
    *
    * 0 = "(11,0)"
    * 1 = "(0,0)"
    * 2 = "(0,4)"
    * 3 = "(0,0)"
    * 4 = "(5,5)"
    * 5 = "(0,0)"
    * 6 = "(0,10)"
    * 7 = "(0,0)"
    * 8 = "(-12,0)"
    *
    * @param matrix
    * @return
    */
  def calc(matrix: Seq[(Int,Int)]): (Int,Int) ={
    matrix.reduceLeft((x,y) => (x._1+y._1,x._2+y._2))
  }

  def main(args:Array[String]): Unit ={
    val in  = new java.util.Scanner(System.in)
    val n= in.nextInt

    val matrix:IndexedSeq[(Int, Int)] = for {
      i <- 0 until n
      j <- 0 until n
    } yield{
      val z = in.nextInt
      //Primary diagonal is when the index i and j are the same we only capture those number in
      //the first place of a pair. The secondary diagonal is when the sum of i and j equal the n - 1
      //We only need to capture the diagonal row/column components all others can be zero.
      (if(i == j)z else 0,if(i + j == n - 1)z else 0)
    }
    val r = calc(matrix )
    println(math.abs(r._1-r._2))
  }
}