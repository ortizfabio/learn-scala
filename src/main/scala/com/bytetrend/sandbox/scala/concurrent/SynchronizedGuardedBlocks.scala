package com.bytetrend.sandbox.scala.concurrent

/**
  * Created by db2admin on 17/07/09.
  */
object SynchronizedGuardedBlocks extends App {
  val lock = new AnyRef
  var message: Option[String] = None
  val greeter = new Thread {
    lock.synchronized {
      while (message == None) lock.wait()
      log(message.get)
    }
  }
  lock.synchronized {
    message = Some("Hello!")
    lock.notify()
  }
  greeter.join()

  def log(msg:String)={
    println(msg)
  }
}
