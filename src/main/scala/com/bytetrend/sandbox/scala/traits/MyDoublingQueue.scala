package com.bytetrend.sandbox.scala.traits

import scala.collection.mutable.ArrayBuffer

abstract class IntQueue {
  def get(): Int

  def put(x: Int)
}

class BasicIntQueue extends IntQueue {
  private val buf = new ArrayBuffer[Int]

  override def get: Int = {
    buf.remove(0)
  }

  override def put(x: Int): Unit = {
    buf += x
  }
}

trait Doubling extends IntQueue {
  abstract override def put(x: Int) {
    super.put(2 * x)
  }
}

trait Incrementing extends IntQueue {
  abstract override def put(x: Int) {
    super.put(x + 1)
  }
}

class MyDoublingQueue extends BasicIntQueue with Doubling

class MyIncrementingQueue extends BasicIntQueue with Incrementing



object Linearization1 extends App {

    val testDoubling = new MyDoublingQueue
    testDoubling.put(10)
    println(testDoubling.get)
    val testIncrementing = new MyIncrementingQueue
    testIncrementing.put(10)
    println(testIncrementing.get)

    val q = new BasicIntQueue with Incrementing with Doubling
    q.put(42) // which puts 85 fist calls doubling then calls increment
    println(q.get)

}

object Linearization3 extends App{
  class A {
    def foo() = "A"
  }
  trait B extends A {
    override def foo() = "B" + super.foo()
  }
  trait C extends B {
    override def foo() = "C" + super.foo()
  }
  trait D extends A {
    override def foo() = "D" + super.foo()
  }
  //http://stackoverflow.com/questions/34242536/linearization-order-in-scala
  /**
    * d = Lin(B) >> Lin(C) >> Lin(D) >> Lin(A)
    * d = (B >> A) >> (C) >> (D)
    * d = D C A B
    */
  var d = new A with D with C with B
  println(d.foo) // CBDA????
}

object Linearization2 extends App{
    class AA { print("AA") }
    trait HH { print("HH") }
    trait SS extends HH { print("SS") }
    trait RR { print("RR") }
    trait TT extends RR with HH { print("TT") }
    class BB extends AA with TT with SS { print("BB") }
    /**
      */
    new BB // AA RR HH TT SS BB
}