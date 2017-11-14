package com.bytetrend.sandbox.scala.challenge

import scala.collection.mutable

class BlobBoundary extends App {
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
    var set: mutable.Set[(Int, Int)] = mutable.Set()[(Int, Int)]

    def findFirst(matrix: Array[Array[Boolean]]): Option[(Int, Int)] = {
      val r2: Iterator[(Int, Int)] =for (x <- matrix.indices.toIterator; y <- matrix(x).indices.toIterator; set += (x,y);  if matrix(x)(y) == true)
        yield (x, y)
      if (r2.hasNext)
        Some(r2.next)
      else
        None
    }

  }
}