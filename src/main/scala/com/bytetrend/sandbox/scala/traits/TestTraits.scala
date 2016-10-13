package com.bytetrend.sandbox.scala.traits

//http://stackoverflow.com/questions/36945333/is-it-possible-to-call-an-overriden-method-from-self-type

class Foo1 {
  def foo = "foo"
}

trait Bar1 extends Foo1 {
  override def foo = super.foo + "bar"
}

class Foo2 {
  def foo = defaultFoo;
  def defaultFoo = "foo"
}

trait Bar2 {
  self: Foo2 =>
  override def foo = self.defaultFoo + "bar"
}

class Foo { def foo = "foo" }
trait Bar { self: Foo =>
  //DOES NOT WORK
//  override def foo = super.foo +"bar"
}
object TestTraits {

  def main(args: Array[String]): Unit = {

    val foo1 = new Foo1 with Bar1
    printf(foo1.foo)

    val foo2 = new Foo2 with Bar2
    printf((foo2.foo == "foobar").toString)
    val foo20 = new Foo2
    printf((foo20.foo == "foo").toString)

    val foo = new Foo with Bar
    printf((foo.foo == "foo").toString)
  }
}