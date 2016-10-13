package com.bytetrend.sandbox.scala

import scala.reflect.ClassTag

/**
  * Created by db2admin on 6/10/2016.
  */
object  GetClassName {


  def className[T](v: T)(implicit ev: ClassTag[T]) = ev.toString

  def main(args: Array[String]): Unit = {
    val l1 = List("a", "b")
    println(className(l1))
  }
}