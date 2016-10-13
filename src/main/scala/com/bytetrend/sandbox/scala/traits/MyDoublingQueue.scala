package com.bytetrend.sandbox.scala.traits

import scala.collection.mutable.ArrayBuffer

abstract class IntQueue {
  def get():Int
  def put(x: Int)
}

class BasicIntQueue extends IntQueue{
  private val buf = new ArrayBuffer[Int]

  override def get(): Int = buf.remove(0)

  override def put(x: Int): Unit = {buf += x}
}

trait Doubling extends IntQueue{
  abstract override def put(x: Int){super.put(2 * x)}
}

trait Incrementing extends IntQueue{
  abstract override def put(x: Int){super.put(x+1)  }
}

class MyDoublingQueue extends BasicIntQueue with Doubling

class MyIncrementingQueue extends BasicIntQueue with Incrementing

object MyDoublingQueue {
  def main(args: Array[String]): Unit = {
    val testDoubling = new MyDoublingQueue
    testDoubling.put(10)
    println(testDoubling.get())

    val testIncrementing = new MyIncrementingQueue
    testIncrementing.put(10)
    println(testIncrementing.get())
  }
}