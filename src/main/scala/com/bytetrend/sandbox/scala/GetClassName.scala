package com.bytetrend.sandbox.scala

import scala.reflect.ClassTag

/**
  * http://stackoverflow.com/questions/19386964/i-want-to-get-the-type-of-a-variable-at-runtime
  */
object  GetClassName {


  def className[T](v: T)(implicit ev: ClassTag[T]) = ev.toString

  import scala.reflect.ClassTag
  def f1(a: Any, b: Any) = {
    val B = ClassTag(b.getClass)
    ClassTag(a.getClass) match {
      case B => "a is the same class as b"
      case _ => "a is not the same class as b"
    }
  }
  def f2[A, B: ClassTag](a: A, b: B) = a match {
    case _: B => s"A  is a B ${className(b)}"
    case _ => "A is not a B"
  }
  def main(args: Array[String]): Unit = {
    val l1 = List("a", "b")
    println(className(l1))
    val x: Any = 5
    val y = 5
    val z = 'c'

    println(f1(x, y) == f1(y, x) )// true, a is the same class as b

    println(f2(x,y))// A (Char) is not a B (Int)
    println(f2(x,z))// A (Char) is a B (Any)
  }
}