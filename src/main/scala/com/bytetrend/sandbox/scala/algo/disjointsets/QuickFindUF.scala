package com.bytetrend.sandbox.scala.algo.disjointsets

import java.util.Scanner

import scala.annotation.tailrec
import scala.io.StdIn


/**
  * Initializes an empty union-find data structure with
  * {@code n} elements {@code 0} through {@code n-1}.
  * Initially, each elements is in its own set.
  *
  * @param  n the number of elements
  * @throws IllegalArgumentException if { @code n < 0}
  */
class QuickFindUF(val n: Int) {
  private val id: Array[Int] = Array.ofDim(n)
  var count = n
  for (i <- 0 until n)
    id(i) = i

  def validate(p: Int) = {
    assert(p >= 0 && p < id.length)
  }

  /**
    * Returns the canonical element of the set containing element {@code p}.
    *
    * @param  p an element
    * @return the canonical element of the set containing { @code p}
    * @throws IllegalArgumentException unless { @code 0 <= p < n}
    */
  def find(p: Int): Int = {
    validate(p)
    id(p)
  }

  /**
    * Returns true if the two elements are in the same set.
    *
    * @param  p one element
    * @param  q the other element
    * @return { @code true} if { @code p} and { @code q} are in the same set;
    *         { @code false} otherwise
    * @throws IllegalArgumentException unless
    *                                  both { @code 0 <= p < n} and { @code 0 <= q < n}
    * @deprecated Replace with two calls to { @link #find(int)}.
    */
  @Deprecated
  def connected(p: Int, q: Int): Boolean = {
    validate(p)
    validate(q)
    id(p) == id(q)
  }

  def union(p: Int, q: Int) = {
    validate(p)
    validate(q)
    val pID = id(p)
    val qID = id(q)
    if (pID != qID) {
      for (i <- 0 until n)
        if (id(i) == pID) id(i) = qID
      count -= 1
    }
    println(this.toString)
  }

  override def toString: String = s"parent: ${id.mkString(",")} count: $count"

}

object QuickFindUF extends App {
  @inline def defined(line: String) = {
    line != null && line.nonEmpty
  }
  val scanner = new Scanner(System.in)
  val n = scanner.nextInt()
  val uf = new QuickUnionUF(n)
  Iterator.continually(Console.readLine).takeWhile(defined(_))
    .foreach(str => {
      val tokens = str.split("\\s")
      val p = tokens(0).toInt
      val q = tokens(1).toInt
      uf.union(p, q)
      println(s"$p $q")
    })
  println(uf.toString)
}
