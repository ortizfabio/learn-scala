package com.bytetrend.sandbox.scala.collection

/**
 * Created by db2admin on 5/11/2016.
 */
class Queue(size: Int) {
  val N = size
  var rear:Int = 0
  var front:Int = 0
  val array = Array.ofDim[Int](size)

  def remove():Int = {
    array(front)
  }

}
