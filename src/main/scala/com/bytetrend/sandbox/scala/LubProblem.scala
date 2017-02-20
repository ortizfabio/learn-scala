package com.bytetrend.sandbox.scala

/**
  * https://gist.github.com/retronym/341475
  *https://github.com/akka/akka/issues/19418
  * http://stackoverflow.com/questions/28024061/understanding-of-upperbound-and-lowerbound-in-scala
  */
object LubProblem extends App{

  object lub {

    case class L[A, B]() {
      //This is the original and it does not work.
  //    def ToLub[AA >: A <: L, BB >: B <: L, L] = new { type LUB = L }
      def ToLub[ A <: L,  B <: L, L] = new { type LUB = L }
    }

    def apply[A, B] = L[A, B].ToLub

  }

  class Fruit

  class Kiwi extends Fruit

  class Apple extends Fruit

  val kal = lub[Kiwi, Apple]
  println(kal)
}
