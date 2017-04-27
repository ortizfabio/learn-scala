package com.bytetrend.sandbox.scala.algo

import scala.collection.mutable.ArrayBuilder
import scala.collection.{AbstractIterator, Iterator, Seq, mutable}


//    val m = mutable.HashMap[Char, Int]()
//   val temp:Map[Char,Int] = str.toCharArray.map(e => (e, m.getOrElseUpdate(e, m.size)))(collection.breakOut)
//    val (es, is) = temp.toList.sortBy (_._2).unzip
//   println(es.toBuffer)
//    println(is.toArray.mkString(","))
/**
  * http://stackoverflow.com/questions/40752319/algorithm-to-list-unique-permutations-of-string-with-duplicate-letters
  * Generating all permutations of a string generating all permutations will cost you O(n!) with no duplicates
  * If it has duplicates then: O( n!/(xi!*xi-1!) you need to divide n! by the factoria count of
  * each letter
  *
  */
object ListUniqPermutationOfStringWithDuplicateLetters extends App {

  implicit object CharArrayOrdering extends Ordering[Array[Char]] {
    def compare(a: Array[Char], b: Array[Char]): Int = {
      val c = a.mkString.compare(b.mkString)
      c
    }
  }

  /**
    * @param args
    */
  override def main(args: Array[String]): Unit = {
    val str = (new java.util.Scanner(System.in)).nextLine
    val aList = permutations(str.toCharArray).toList.sorted
    aList.foreach(x => println(x.mkString(",")))
  }

  def permutations(seq: Array[Char]): Iterator[Array[Char]] =
    if (seq.isEmpty) Iterator(seq)
    else new PermutationsItr(seq)

  private class PermutationsItr(seq: Array[Char]) extends AbstractIterator[Array[Char]] {
    private[this] val (elms, idxs) = init()
    println("idxs0 "+idxs.mkString)
    private var _hasNext = true

    def hasNext = _hasNext

    def next(): Array[Char] = {
      if (!hasNext)
        Iterator.empty.next()

      val forcedElms = new mutable.ArrayBuffer[Char](elms.size) ++= elms
      println("forcedElms "+forcedElms.mkString)
      val result:Array[Char] = ((new ArrayBuilder.ofChar) ++= forcedElms).result()
      println("result1 "+result.mkString)
      var i = idxs.length - 2
      while (i >= 0 && idxs(i) >= idxs(i + 1))
        i -= 1

      if (i < 0) {
        _hasNext = false
      }else {
        var j = idxs.length - 1
        while (idxs(j) <= idxs(i)) j -= 1
        swap(i, j)

        val len = (idxs.length - i) / 2
        var k = 1
        while (k <= len) {
          swap(i + k, idxs.length - k)
          k += 1
        }
        println("idxs"+i+" "+idxs.mkString)
      }
      println("result2 "+result.mkString)
      result
    }

    private def swap(i: Int, j: Int) {
      val tmpI = idxs(i)
      idxs(i) = idxs(j)
      idxs(j) = tmpI
      val tmpE = elms(i)
      elms(i) = elms(j)
      elms(j) = tmpE
    }

    private[this] def init() = {
      val m = mutable.HashMap[Char, Int]()
      val (es, is) = (thisCollection map (e => (e, m.getOrElseUpdate(e, m.size))) sortBy (_._2)).unzip

      (es.toBuffer, is.toArray)
    }

    def thisCollection: Seq[Char] = seq.asInstanceOf[Array[Char]]
  }

}
