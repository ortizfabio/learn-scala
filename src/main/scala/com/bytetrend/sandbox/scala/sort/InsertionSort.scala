package com.bytetrend.sandbox.scala.sort


object InsertionSort {
  def insertionSort(a: Array[Char]): Unit = {
    def swap(i: Int, j: Int): Unit = {
      val t: Char = a(i)
      a(i) = a(j)
      a(j) = t
      println(a.mkString(" "))
    }

    val n = a.length
    for (i: Int <- 1 to n - 1) {
      var j: Int = i
      while (j > 0 && a(j - 1) > a(j)) {
        swap(j, j - 1)
        j -= 1
      }

    }
  }

  def main(args: Array[String]): Unit = {
    //F G H E A D C B
    val test: Array[Char] = Array('B', 'C', 'D', 'A', 'E', 'H', 'G', 'F').reverse
    println(test.mkString(" "))
    insertionSort(test)
    println(test.mkString(" "))
  }

}

object InsertionSort2 {

  def isort[T](xs: List[T])(implicit ordering: Ordering[T]): List[T] = xs match {
    case List() => {
      println("isort1 "+List())
      List()
    }
    case x :: xs1 => {
      val l = insert(x, isort(xs1))
      println("isort2 "+l)
      l
    }
  }

  def insert[T](x: T, xs: List[T])(implicit ordering: Ordering[T]): List[T] = xs match {
    case List() => {
      println("insert1 "+List(x))
      List(x)
    }
    case y :: ys => {
      if (ordering.lteq(x,y)) {
        println("insert2 "+ (x :: xs))
        x :: xs
      }
      else{
       val l = y :: insert(x, ys)
        println("insert3 "+l)
        l
      }
    }
  }

  def main(args: Array[String]): Unit = {
    //F G H E A D C B
    val test: List[Char] = List('F', 'G', 'D', 'A', 'E', 'H', 'C', 'B')
    println(test.mkString(" "))
    isort(test)
    println(isort(test).mkString(" "))
  }

}