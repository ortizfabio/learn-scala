package com.bytetrend.sandbox.scala.collection

/**
  * Created by db2admin on 8/7/2016.
  */
object Lists {

  def squarList1(xs: List[Int]):List[Int] =
    xs match {
      case Nil => xs
      case y :: ys => y*y :: squarList1(ys)
    }
  def squartList2(xs: List[Int]):List[Int]= {
    xs map (x => x*x)
  }

  def pack[T](xs:List[T]):List[List[T]]= xs match {
    case Nil => Nil
    case x :: xs1 =>
      val(first,rest)= xs span (y => y == x)
      first :: pack(rest)
  }
  def encode(xs : List[String]):List[(String,Int)]={
    def encode0(l:List[List[String]]):List[(String,Int)] = l match {
      case Nil => Nil
      case x :: xs1 =>
        val y = (x.head, x.length)
        y :: encode0(xs1)
    }
    encode0(pack(xs))
  }

  println(encode(List("a", "a", "a", "b", "c", "c", "a")))

}
