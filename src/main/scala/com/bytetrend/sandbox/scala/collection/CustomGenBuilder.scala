package com.bytetrend.sandbox.scala.collection

/**
  * http://stackoverflow.com/questions/24354321/type-polymorphic-implicit-class-compile-errors
  */
object CustomGenBuilder {

  trait FooLike[A] {
    def foo(a: A): String
  }

  object FooLike {

    implicit object FooLikeInt extends FooLike[Int] {
      override def foo(a: Int): String = a.toString
    }

  }

  implicit class GenBuilder[T](val self: T)(implicit f: FooLike[T]) {

    private var _items = Set(self)

    def |?|(that: GenBuilder[T]): GenBuilder[T] = {
      _items = _items + that.self
      this
    }

    def items: Set[String] = _items.toSet.map((a: T) => f.foo(a))
  }

}

object Test extends App{
  import CustomGenBuilder._
  import CustomGenBuilder.FooLike._
  def test = {
    val res = 11 |?| 12 |?| 13 |?| 14
    println(res.items)
  }

  test
}