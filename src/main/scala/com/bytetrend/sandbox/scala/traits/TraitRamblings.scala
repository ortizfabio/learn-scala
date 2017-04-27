package com.bytetrend.sandbox.scala.traits

/**
  * 
  */
object TraitRamblings extends App{

  trait FooLike {
    var bar: Int
  }

  object Foo extends FooLike {
    private var x = 0

    def bar = x

    def bar_=(value: Int) {
      x = value
    }
  }

  println(Foo.bar)
  Foo.bar_=(2)
  println(Foo.bar)

}
