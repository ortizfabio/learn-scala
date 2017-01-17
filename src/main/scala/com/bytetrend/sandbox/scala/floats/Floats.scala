package com.bytetrend.sandbox.scala.floats

/**
  * Created by db2admin on 1/16/2017.
  */
object Floats extends App {

  val x1: Double = 0.3
  val x2: Double = 0.1 + 0.1 + 0.1
  println(x1 == x2)

  val z1 = 0.5
  val z2 = 0.1 + 0.1 + 0.1 + 0.1 + 0.1
  println(z1 == z2)

  def calc(c: Double): Double= {
    val epsilon: Double = 1e-15
    var t: Double = c
    while (Math.abs (t * t - c) > c * epsilon)
    t = (c / t + t) / 2.0
    t
  }

  println(calc(20))
  println(calc(4))
  println(calc(10))
}