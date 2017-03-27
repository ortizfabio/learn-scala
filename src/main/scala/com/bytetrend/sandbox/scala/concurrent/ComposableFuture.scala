package com.bytetrend.sandbox.scala.concurrent

import scala.concurrent.ExecutionContext.Implicits.global

import scala.util.{Failure, Random, Success}

import scala.concurrent.Future
import scala.util.Random


object ComposableFuture {
  val x = Future(RandomNumberFactory.getRandom(10))

  def composableFutures = {
    // composable code
    val result: Future[Boolean] = x.map { number =>
      if (RandomNumberFactory.isDivisibleBy(3, number))
        true
      else
        throw new Exception("not divisible by 3")
    }
    result.onComplete {
      case Success(e) => println(s"the result is ${e}")
      case Failure(e) => println(e.getMessage)
    }
  }
}

object RandomNumberFactory {

  val random = Random

  def getRandom(n: Int): Int = {
    random.nextInt(n)
  }

  def isDivisibleBy(divisor: Int, numerator: Int): Boolean = {
    if (divisor > numerator)
      false
    else if (numerator % divisor == 0)
      true
    else
      false
  }
}