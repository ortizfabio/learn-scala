package com.bytetrend.sandbox.scala.impliciting


/**
  * The implicits are defined at the package level (impliciting)
  */
object StringImplicits extends App{
  println("HAL".increment)
  println("IBM".decrement)

  println("4".plusOne)

  println("0".asBoolean)
}
