package com.bytetrend.sandbox.scala.collection

import scala.reflect.ClassTag

/**
  * https://www.chrisstucchio.com/blog/2014/cannot_find_classtag_for_element_type_T.html
  */
class Foo[+T] {
  def someArray[U >: T](size: Int)(implicit m: ClassTag[U]): Array[U] = {
    val result = new Array[U](size)

    result
  }
}
class A[T: Manifest] { def apply(sz:Int) = new Array[T](sz) }

/**
  * http://stackoverflow.com/questions/24198735/scala-generic-array-inside-trait
  * @tparam T
  */
trait THasArray[T >: Null]
{
  private var table: Seq[T] = null

  protected def init(elems: (Int, T)*)(implicit manifest: Manifest[T]) =
  {
    val size = (elems foldLeft 0)(_ max _._1)
    val array = Array.fill[T](size + 1)(null)
    elems foreach { x => array(x._1) = x._2 }
    table = array
  }
}