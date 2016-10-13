package com.bytetrend.sandbox.scala

/**
  * Created by db2admin on 6/17/2016.
  */
package object example {
  implicit def intToRational(x: Int) = new Rational(x)
}
