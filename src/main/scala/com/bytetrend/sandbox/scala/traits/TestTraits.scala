package com.bytetrend.sandbox.scala.traits

import com.sun.xml.internal.fastinfoset.algorithm.BuiltInEncodingAlgorithmState

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

class Foo {
  def foo = "foo"
}

trait Bar {
  self: Foo =>
  //DOES NOT WORK
  //  override def foo = super.foo +"bar"
}

//Defining a val in a trait
trait Animal {
  val x: String

  require(x != null, "x was null")
}

class Pet extends {

  val x = "initialized x from animal"

} with Animal

class Beast(val x: String) extends Animal

object TestTraits {

  def main(args: Array[String]): Unit = {

    val foo1 = new Foo1 with Bar1
    println(foo1.foo)

    val foo2 = new Foo2 with Bar2
    println((foo2.foo == "foobar").toString)

    val foo20 = new Foo2
    println((foo20.foo == "foo\n").toString)

    val foo = new Foo with Bar
    println((foo.foo == "foo\n").toString)

    val aPet = new Pet
    println(aPet.x)

    val aBeast = new Beast("Grrrr!")
    println(aBeast.x)

    //Will trigger Exception in Animal
    val anotherBeast = new Beast(null)

  }

}