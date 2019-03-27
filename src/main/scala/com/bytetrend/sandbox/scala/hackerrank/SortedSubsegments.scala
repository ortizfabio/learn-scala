package com.bytetrend.sandbox.scala.hackerrank

import java.io.PrintWriter

import scala.io.Source
import org.scalameter._

import scala.collection.mutable.ListBuffer

/** Consider an array A =[a0,a1,...,an-1\ of n integers.
  * We perform q queries of the following type on A:
  * *
  * Sort all the elements in the subsegment
  * *
  * .
  * *
  * Given
  * , can you find and print the value at index (where ) after performing
  * *
  * queries?
  * *
  * Input Format
  * *
  * The first line contains three positive space-separated integers describing the respective values of
  * (the number of integers in ), (the number of queries), and (an index in ).
  * The next line contains space-separated integers describing the respective values of .
  * Each line of the subsequent lines contain two space-separated integers describing the respective and values for query
  * *
  * .
  * *
  * Constraints
  * *
  * Output Format
  * *
  * Print a single integer denoting the value of
  * after processing all
  * *
  * queries.
  * *
  * Sample Input 0
  * *
  * 3 1 1
  * 3 2 1
  * 0 1
  * *
  * Sample Output 0
  * *
  * 3
  * *
  * Explanation 0
  * *
  *
  * There is only one query to perform. When we sort the subarray ranging from index to index , we get . We then print the element at index , which is
  * *
  * .
  * *
  * Sample Input 1
  * *
  * 4 2 0
  * 4 3 2 1
  * 0 2
  * 1 3
  * *
  * Sample Output 1
  * *h
  * 2
  * *
  * Explanation 1
  * *
  *
  * There are
  * *
  * queries:
  * *
  * When we sort the subarray ranging from index
  * *
  * to index , we get
  * .
  * When we sort the subarray of
  * from index to index , we get
  * *
  * .
  * *
  * Having performed all of the queries, we print the element at index
  * , which is .
  */
object SortedSubsegments {


  // Main function that sorts arr[l..r] using
  // merge()
  def mergesort(arr: Array[Int], l: Int, r: Int): Unit = {
    // Merges two subarrays of arr[].
    // First subarray is arr[l..m]
    // Second subarray is arr[m+1..r]
    def merge(arr: Array[Int], l: Int, m: Int, r: Int): Unit = { // Find sizes of two subarrays to be merged
      val n1 = m - l + 1
      val n2 = r - m

      /* Create temp arrays */
      val L = Array.ofDim[Int](n1)
      val R = Array.ofDim[Int](n2)

      /*Copy data to temp arrays*/
      for (i <- 0 until n1) L(i) = arr(l + i)
      for (j <- 0 until n2) R(j) = arr(m + 1 + j)

      /* Merge the temp arrays */
      // Initial indexes of first and second subarrays
      var i = 0
      var j = 0

      // Initial index of merged subarry array
      var k = l
      while (i < n1 && j < n2) {
        if (L(i) <= R(j)) {
          arr(k) = L(i)
          i += 1
        }
        else {
          arr(k) = R(j)
          j += 1
        }
        k += 1
      }

      /* Copy remaining elements of L[] if any */
      while (i < n1) {
        arr(k) = L(i)
        i += 1
        k += 1
      }

      /* Copy remaining elements of R(] if any */
      while (j < n2) {
        arr(k) = R(j)
        j += 1
        k += 1
      }
    }

    if (l < r) { // Find the middle point
      val m = (l + r) / 2
      // Sort first and second halves
      mergesort(arr, l, m)
      mergesort(arr, m + 1, r)
      // Merge the sorted halves
      merge(arr, l, m, r)
    }
  }

  def quicksort(xs: Array[Int], left: Int, right: Int) {
    def swap(i: Int, j: Int) {
      val t = xs(i);
      xs(i) = xs(j);
      xs(j) = t
    }

    def sort1(l: Int, r: Int) {
      val pivot = xs((l + r) / 2)
      var i = l
      var j = r
      while (i < j) {
        while (xs(i) < pivot) i += 1
        while (xs(j) > pivot) j -= 1
        if (i <= j) {
          swap(i, j)
          i += 1
          j -= 1
        }
      }
      if (l < j) sort1(l, j)
      if (i < r) sort1(i, r)
    }

    sort1(left, right)
  }

  def insertionsort(a: Array[Int], left: Int, right: Int): Unit = {
    def swap(i: Int, j: Int): Unit = {
      val t = a(i)
      a(i) = a(j)
      a(j) = t
    }


    for (i <- left to right) {
      var j = i
      while (j > left && a(j - 1) > a(j)) {
        swap(j, j - 1)
        j -= 1
      }
    }
  }


  def isSorted(a: Array[Int], left: Int, right: Int): Boolean = {
    var i = left
    while (i < right && a(i) <= a(i + 1)) i += 1
    i == right
  }

  def searchAndShiftLeft(a: Array[Int], sourceLeft: Int, sourceRight: Int, destLeft: Int, destRight: Int): Unit = {
    /**
      * shift each elements left and left most element will be at the last index of range (end) on the right
      *
      * @param a    array of elements to shift
      * @param src  source right most index in array (begining)
      * @param dest dest left most index in array (end)
      */
    def shiftLeft(a: Array[Int], src: Int, dest: Int): Unit = {
      val t = a(dest)
      var index = dest
      while (index < src) {
        a(index) = a(index + 1)
        index += 1
      }
      a(index) = t
    }

    var source = sourceRight
    var dest = destRight
    while (source > destLeft) {
      while (source >= destRight && dest >= destLeft) {
        if (a(source) >= a(dest))
          source -= 1
        else {
          if (a(source) < a(dest)) {
            shiftLeft(a, source, dest)
            dest -= 1
          }
        }
      }
      source -= 1
    }
  }

  def searchAndShiftRight(a: Array[Int], destLeft: Int, destRight: Int, sourceLeft: Int, sourceRight: Int): Unit = {
    /**
      * shift each element right and right most (end) element should go to first index (begining) on the left.
      *
      * @param a      array of values
      * @param source left most index in array (begining)
      * @param dest   right most index in array (end)
      */
    def shiftRight(a: Array[Int], source: Int, dest: Int): Unit = {
      val t = a(dest)
      var index = dest
      while (index > source) {
        a(index) = a(index - 1)
        index -= 1
      }
      a(source) = t
    }

    var source = destLeft
    var dest = sourceLeft
    while (source < sourceRight) {
      while (source <= destRight && dest <= sourceRight) {
        if (a(source) <= a(dest))
          source += 1
        else {
          if (a(source) > a(dest)) {
            shiftRight(a, source, dest)
            dest += 1
          }
        }
      }
      source += 1
    }
  }

  /*
    * Write your code here.
    */
  /*
   * Complete the sortedSubsegments function below.
   */
  def sortedSubsegments(k: Int, a: Array[Int], queries: Array[Array[Int]]): Int = {
    object SortType extends Enumeration {
      type SortType = Value
      val INSERT_SORT = Value
      val QUICK_SORT = Value
      val BUBBLE_SORT = Value
    }
    case class Point(left: Int, right: Int)
    //   println("           ->     %s".format(a.mkString(", ")))
    var sortedSegments = ListBuffer[Point]()
    var sortType = SortType.QUICK_SORT

    def skip(newSeg: Point): Boolean = {
      //val length = newSeg.right - newSeg.left
      val MIN_SORT_CNT = 100
      var skip = false
      var curSeg = Point(newSeg.left, newSeg.right)
      sortedSegments = sortedSegments.foldLeft(ListBuffer[Point]())((list, storeSeg) => {
        if (skip) {
          if (!(curSeg.left >= storeSeg.left && curSeg.right <= storeSeg.right))
            list += storeSeg
        } else {
          val overlapLeft = (curSeg.left < storeSeg.left && curSeg.right >= storeSeg.left)
          val overlapRight = (curSeg.right > storeSeg.right && curSeg.left <= storeSeg.right)
          if (overlapLeft) {
            if (!overlapRight) {
              if (storeSeg.right - (curSeg.right + 1) > 1)
                list += Point(curSeg.right + 1, storeSeg.right) // add point but trim left
              list += curSeg
              if (curSeg.right - curSeg.left + 1 < MIN_SORT_CNT)
                insertionsort(a, curSeg.left, storeSeg.left - 1)
              else
                quicksort(a, curSeg.left, storeSeg.left - 1)
              searchAndShiftLeft(a, storeSeg.left, curSeg.right, curSeg.left, storeSeg.left - 1)
              curSeg = Point(curSeg.left, storeSeg.right)
              skip = true
            }

            //it overlaps right and left then skip curSeg
          } else if (overlapRight) {
            if ((curSeg.left - 1) - storeSeg.left > 1)
              list += Point(storeSeg.left, curSeg.left - 1)
            list += curSeg
            if (curSeg.right + 1 - curSeg.left < MIN_SORT_CNT)
              insertionsort(a, storeSeg.right + 1, curSeg.right)
            else
              quicksort(a, storeSeg.right + 1, curSeg.right)
            searchAndShiftRight(a, curSeg.left, storeSeg.right, storeSeg.right + 1, curSeg.right)
            curSeg = Point(storeSeg.left, curSeg.right)
            skip = true
          } else {
            if (curSeg.left >= storeSeg.left && curSeg.right <= storeSeg.right)
              skip = true //if curSeg is equal to an existing one skip
            list += storeSeg //no overlapping add current segment
          }
        }
        list
      })
      if (!skip)
        sortedSegments += curSeg
      skip
    }

    var totalCount = 0
    var sortCount = 0
 //   println("           -> %s".format((0 until a.length).foldLeft(List[String]())({ (z, f) => z :+ "%2d".format(f) }).mkString(", ")))
    //    println("%2d )       -> %s %s".format(totalCount, sortType.toString, a.foldLeft(List[String]())({ (z, f) => z :+ "%2d".format(f) }).mkString(", ")))
    if (!isSorted(a, 0, a.length - 1))
      for (row <- queries) {
        if (!skip(Point(row(0), row(1)))) {
          if (sortType == SortType.INSERT_SORT)
            insertionsort(a, row(0), row(1))
          else
            quicksort(a, row(0), row(1))
          sortCount += 1
        }
 //       println("%2d ) %2d %2d -> %s".format(totalCount, row(0), row(1), a.foldLeft(List[String]())({ (z, f) => z :+ "%2d".format(f) }).mkString(", ")))
        totalCount += 1
      }
//    println(s"$sortCount $totalCount")
    a(k)
  }

  def main(args: Array[String]) {
    val standardConfig = config(
      Key.exec.minWarmupRuns -> 20,
      Key.exec.maxWarmupRuns -> 40,
      Key.exec.benchRuns -> 25,
      Key.verbose -> true
    ) withWarmer (new Warmer.Default)

    val printWriter = new PrintWriter(System.out)
    val s1 = getClass.getClassLoader.getResourceAsStream("sortedsubsegment.5.txt")
    val lines = Source.fromInputStream(s1).getLines()
    var line: String = lines.next
    val nqk = line.toString.split(" ")

    val n = nqk(0).trim.toInt

    val q = nqk(1).trim.toInt

    val k = nqk(2).trim.toInt
    line = lines.next
    val a = line.toString.split(" ").map(_.trim.toInt)

    val queries = Array.ofDim[Int](q, 2)

    for (i <- 0 until (q)) {
      line = lines.next
      queries(i) = line.toString.split(" ").map(_.trim.toInt)
    }
    val seqtime = standardConfig measure {

    val result = sortedSubsegments2(k, a, queries)

    printWriter.println(result)
    }
       println(s" time: $seqtime ms")

    printWriter.close()
  }


  def sortedSubsegments2(k: Int, a: Array[Int], queries: Array[Array[Int]]): Int = {
    //    println("           -> %s".format(a.mkString(", ")))
    var sortedSegments = ListBuffer[(Int, Int)]()

    var totalCount = 0
    var sortCount = 0
//    println("           -> %s".format((0 until a.length).foldLeft(List[String]())({ (z, f) => z :+ "%2d".format(f) }).mkString(", ")))
    for (row <- queries) {
      insertionsort(a, row(0), row(1))
      sortCount += 1
//      println("%2d ) %2d %2d -> %s".format(totalCount, row(0), row(1), a.foldLeft(List[String]())({ (z, f) => z :+ "%2d".format(f) }).mkString(", ")))
      totalCount += 1
    }
//    println(s"$sortCount $totalCount")
    a(k)
  }

  /*
    * Write your code here.
    */
  /*
   * Complete the sortedSubsegments function below.
   */
  def sortedSubsegments3(k: Int, a: Array[Int], queries: Array[Array[Int]]): Int = {
    object SortType extends Enumeration {
      type SortType = Value
      val INSERT_SORT = Value
      val QUICK_SORT = Value
    }
    case class Point(left: Int, right: Int)
    println("           ->     %s".format(a.mkString(", ")))
    var sortedSegments = ListBuffer[Point]()
    var sortType = SortType.QUICK_SORT

    def skip(newSeg: Point): Boolean = {
      sortType = SortType.QUICK_SORT
      val length = newSeg.right - newSeg.left
      val INSERT_PERCENT = 0.7
      var skip = false
      sortedSegments = sortedSegments.foldLeft(ListBuffer[Point]())((list, curSeg) => {
        val overlapLeft = (newSeg.left < curSeg.left && newSeg.right >= curSeg.left)
        val overlapRight = (newSeg.right > curSeg.right && newSeg.left <= curSeg.right)
        if (overlapLeft) {
          if (!overlapRight) {
            list += Point(newSeg.right, curSeg.right) // add point but trim left
            val range = newSeg.right - curSeg.left
            if (range.toFloat / length > INSERT_PERCENT)
              sortType = SortType.INSERT_SORT
          }
          //it overlaps right and left then skip curSeg
        } else if (overlapRight) {
          list += Point(curSeg.left, newSeg.left) //add point but trim right
          val range = curSeg.right - newSeg.left
          if (range.toFloat / length > INSERT_PERCENT)
            sortType = SortType.INSERT_SORT
        } else {
          if (newSeg.left >= curSeg.left && newSeg.right <= curSeg.right)
            skip = true //if newSeg is equal to an existing one skip
          list += curSeg //no overlapping add current segment
        }
        list
      })
      if (!skip)
        sortedSegments += newSeg
      skip
    }

    var totalCount = 0
    var sortCount = 0
    println("           ->     %s".format((0 until a.length).foldLeft(List[String]())({ (z, f) => z :+ "%2d".format(f) }).mkString(", ")))
    if (!isSorted(a, 0, a.length - 1))
      for (row <- queries) {
        if (!skip(Point(row(0), row(1)))) {
          if (sortType == SortType.INSERT_SORT)
            insertionsort(a, row(0), row(1))
          else
            quicksort(a, row(0), row(1))
          sortCount += 1
        }
        println("%2d ) %2d %2d -> %s".format(totalCount, row(0), row(1), a.foldLeft(List[String]())({ (z, f) => z :+ "%2d".format(f) }).mkString(", ")))
        totalCount += 1
      }
    println(s"$sortCount $totalCount")
    a(k)
  }

  def main3(args: Array[String]) {
    val stdin = scala.io.StdIn

    val printWriter = new PrintWriter(System.out)

    val nqk = stdin.readLine.split(" ")

    val n = nqk(0).trim.toInt

    val q = nqk(1).trim.toInt

    val k = nqk(2).trim.toInt

    val a = stdin.readLine.split(" ").map(_.trim.toInt)

    val queries = Array.ofDim[Int](q, 2)

    for (queriesRowItr <- 0 until q) {
      queries(queriesRowItr) = stdin.readLine.split(" ").map(_.trim.toInt)
    }

    val result = sortedSubsegments(k, a, queries)

    printWriter.println(result)

    printWriter.close()
  }


  def quicksort2(a: Array[Int], left: Int, right: Int): Unit = {
    def swap(i: Int, j: Int) = {
      val tmp = a(i)
      a(i) = a(j)
      a(j) = tmp
    }

    val pivot = {
      val half = a((left + right) / 2)
      if (right - left > 8) {
        if (a(left) < a(right)) {
          if (half < a(left)) a(left) else Math.min(a(right), half)
        } else {
          if (half < a(right)) a(right) else Math.min(a(left), half)
        }

      } else
        half
    }
    var (i, j) = (left, right)
    while (i < j) {
      while (a(i) < pivot) i += 1
      while (a(j) > pivot) j -= 1
      if (i < j) {
        swap(i, j)
      }
      i += 1
      j -= 1
    }
    if (left < j) quicksort(a, left, j)
    if (i < right) quicksort(a, i, right)
  }


}
