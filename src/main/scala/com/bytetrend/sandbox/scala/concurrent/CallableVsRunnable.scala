package com.bytetrend.sandbox.scala.concurrent

import java.util.ArrayList
import java.util.concurrent.{Callable, Future, Executors}
import scala.collection.JavaConverters._

//import scala.concurrent.{Future,Exec}

/**
 * Created by ortizfabio on 5/30/2016.
 */
class CallableVsRunnable {
  val es = Executors.newFixedThreadPool(2)
  var futures = new ArrayList[MyCallable[Long]]()

  def submit: Unit = {
    for (i: Int <- 0 to 12)
      futures.add(new MyCallable(i))
  }

  def run: Unit = {
    val results = es.invokeAll(futures).asScala
    results.foreach((x: Future[Long]) => {
      try {
        println(x.get())
    } catch {
        case _ :Throwable => println()
      }
    }
    )
    es.shutdown()
  }
}

class MyCallable[T](value: T) extends Callable[T] {
  var id: T = value

  def call: T = {
    val a: Int = 4
    val b: Int = 0
    println("a/b:" + (a / b))
    id
  }
}

object CallableVsRunnable {
  def main(args: Array[String]): Unit = {
    val test: CallableVsRunnable = new CallableVsRunnable
    test.submit
    test.run
  }
}
