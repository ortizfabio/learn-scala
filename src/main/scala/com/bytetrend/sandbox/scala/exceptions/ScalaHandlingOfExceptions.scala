package com.bytetrend.sandbox.scala.exceptions

/**
  * http://blog.xebia.com/try-option-or-either/
  */
object ScalaHandlingOfExceptions {
  import scala.util.control.Exception._

  val i = 0
  val anOption: Option[Int] = catching(classOf[ArithmeticException]) opt { 1 / i }
  val aEither: Either[Throwable, Int] = catching(classOf[ArithmeticException]) either { 1 / i }
}
