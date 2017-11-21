package com.bytetrend.sandbox.scala.challenge

import scala.collection.mutable

/**
  * <Bridgewater>
  */
object BlobBoundary extends App {
  implicit def int2boolean(b: Int) = if (b == 0) false else true

  val test: Array[Array[Boolean]] = Array(
    Array(0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
    Array(0, 0, 1, 1, 1, 0, 0, 0, 0, 0),
    Array(0, 0, 1, 1, 1, 1, 1, 0, 0, 0),
    Array(0, 0, 1, 0, 0, 0, 1, 0, 0, 0),
    Array(0, 0, 1, 1, 1, 1, 1, 0, 0, 0),
    Array(0, 0, 0, 0, 1, 0, 1, 0, 0, 0),
    Array(0, 0, 0, 0, 1, 0, 1, 0, 0, 0),
    Array(0, 0, 0, 0, 1, 1, 1, 0, 0, 0),
    Array(0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
    Array(0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
  )

  def search(matrix: Array[Array[Boolean]]): (Int, Int, Int, Int, Int) = {
    var visited: mutable.Set[(Int, Int)] = mutable.Set()
    var blob: mutable.Set[(Int, Int)] = mutable.Set()
    var top = 0
    var bottom = matrix.length - 1
    var left = 0
    var right = matrix(0).length - 1

    def test(x: Int, y: Int): Boolean = {
      visited += ((x, y))
      matrix(x)(y)
    }

    def findFirst(): Option[(Int, Int)] = {
      val r2: Iterator[(Int, Int)] = for (x <- matrix.indices.toIterator;
                                          y <- matrix(x).indices.toIterator;
                                         if test(x,y) ) yield (x, y)
      if (r2.hasNext)
        Some(r2.next)
      else
        None
    }

    def findBlob(prev: (Int, Int),curr: (Int, Int)): (Int, Int, Int, Int, Int) = {

      (curr._1, curr._2, visited.size, blob.size, 0)

    }

    findFirst match {
      case Some((x, y)) => {
        blob += ((x, y))
        left = y
        findBlob((x, y - 1),(x, y))
      }
      case None => (0, 0, 0, 0, 0)
    }
  }

  println(search(test))
}