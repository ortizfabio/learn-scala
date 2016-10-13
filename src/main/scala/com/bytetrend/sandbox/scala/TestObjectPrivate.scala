package com.bytetrend.sandbox.scala

/**
 * Created by db2admin on 6/8/2016.
 */
private  class TestObjectPrivate(val firstName: String) {
  import TestObjectPrivate._

  def name: String = firstName.reverse+foo
}

object TestObjectPrivate{
  //private[this]
  private def foo =42

  private def myfoo(): Int = foo * 2
}
