package com.bytetrend.sandbox.scala.sort

class DualPivotQuicksort {

  private final val INSERTION_SORT_THRESHOLD: Int = 47

  def sort(a: Array[Int]): Unit = {
    if( a.length < INSERTION_SORT_THRESHOLD)
      sort(a, 0, a.length, true)
    else
      sort2(a)
  }


  def sort2(xs: Array[Int]) {
    def swap(i: Int, j: Int) {
      val t = xs(i);
      xs(i) = xs(j);
      xs(j) = t
    }

    def sort1(start: Int, end: Int) {
      val pivot = xs((start + end) / 2)
      var i = start;
      var j = end
      while (i <= j) {
        while (xs(i) < pivot) i += 1
        while (xs(j) > pivot) j -= 1
        if (i <= j) {
          swap(i, j)
          i += 1
          j -= 1
        }
      }
      if (start < j) sort1(start, j)
      if (j < end) sort1(i, end)
    }

    sort1(0, xs.length - 1)
  }

  def sort(a: Array[Int], left: Int, right: Int, leftmost: Boolean) = {
    val length: Int = right - left + 1
    if (leftmost) {
      var i: Int = left
      var j: Int = left
      while (i < right) {
        var ai: Int = a(i + 1)
        var foundIt: Boolean = false
        while (ai < a(j) && !foundIt) {
          a(j + 1) = a(j)
          if (j == left) {
            foundIt = true
          }
          j -= 1

        }
        a(j + 1) = ai;
        i += 1
        j = i
        println(a.mkString(" "))
      }
    }
  }
}

object DualPivotQuicksort extends App {

  override def main(args: Array[String]): Unit = {
    val sorter: DualPivotQuicksort = new DualPivotQuicksort()
    val arr: Array[Int] = Array(3, 6, 3, 6, 8, 1, 0, 6, 4, 3,33,22,11,9,0,1,4,5,6,5,4,4,3,31,36)
    println(arr.mkString(" "))
    sorter.sort(arr)
    println(arr.mkString(" "))
  }

}