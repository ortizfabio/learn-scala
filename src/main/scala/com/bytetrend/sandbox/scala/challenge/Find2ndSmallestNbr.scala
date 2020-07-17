package com.bytetrend.sandbox.scala.challenge

object Find2ndSmallestNbr {

  def main(args: Array[String]) {
    def result(x:Option[Int])= x match {
      case Some(x) => println(s"second smallest is: $x")
      case _ => println("Array did not contain 2 or more numbers")
    }
    val arr = Array[Int](9, 3, 0, 4, 5, 8, 7, 6, 1, 2)
    result(find2ndSmallestNbr(arr) )
    result(find2ndSmallestNbr(Array[Int]()))
  }

  def find2ndSmallestNbr(arr: Array[Int]): Option[Int] = {
    arr match {
      case a: Array[Int] if a.length > 1 => {
        //(first,second)
        Some(arr.tail.tail.foldLeft(if (arr.head < arr.tail.head) (arr.head, arr.tail.head) else (arr.tail.head, arr.head)) {
          case ((frst, scnd), n) => if (n < scnd) {
            if (n < frst) (n, frst) else (frst, n)
          } else (frst, scnd)
        }._2)
      }
      case _ => None
    }
  }
}
