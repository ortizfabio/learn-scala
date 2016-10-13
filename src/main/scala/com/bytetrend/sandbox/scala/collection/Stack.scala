package com.bytetrend.sandbox.scala.collection

/**
 * Created by db2admin on 4/13/2016.
 */
class Stack (size: Int) {
  var level = size - 1;
  val array = Array.fill[Int](size)(-1)

  def push(i : Int): Unit ={
    if (level < 0) throw new Exception("Stack limit reach")
    array(level) = i
    level -=  1
  }

  def peek(): Int = {
    if (level == size) throw new Exception("Stack is empty")
    val index = level + 1
    array(index)
  }

  def pop(): Int = {
    if (level == size) throw new Exception("Stack is empty")
    level += 1
    val a = array(level)
    array(level)= -1
    a
  }

  override def toString : String ={
    array.toList.toString()+" "+level
  }

}

object Stack {
  def main(args: Array[String]): Unit = {
    val myStack = new Stack(5)
    for(i <- 1 to 5) {
      myStack.push(i)
      println(myStack.peek())
    }
    println(myStack.toString)


    for(i <- 1 to 5) println(myStack.pop())
    println(myStack.toString)
  }
}