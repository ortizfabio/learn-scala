package com.bytetrend.sandbox.scala.concurrent

import scala.collection.mutable
import scala.collection.mutable.MutableList
/**
 * Created by db2admin on 5/13/2016.
 */
class ProducerConsumer extends Thread{

  private val linesToLog : MutableList[String] = new mutable.MutableList[String]()
  @volatile
  private var terminateRequested : Boolean = false

  override def run():Unit = {
    try{
      while( !terminateRequested){
        var line: String = ""
        synchronized(linesToLog){
          if(linesToLog.isEmpty )
            linesToLog.wait
          if(linesToLog.headOption != None)
            linesToLog.head.length
          0
        }
        if(!line.isEmpty)
          log(line)
      }
    }catch {
      case _ : InterruptedException => None
    }
  }


  def log(line : String):Unit = {
    synchronized(linesToLog){
      linesToLog.+=( line)
      linesToLog.notify()
      0
    }
  }
}

object ProducerConsumer {

  def main(args: Array[String]): Unit = {

  }
}
