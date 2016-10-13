package com.bytetrend.sandbox.scala.concurrent

/**
 * Created by db2admin on 10/11/2015.
 */
class Latch(noThreads:Int) {
  private final  val synchObj:Object = new Object();
  protected var count :Int = 0
  
  
  synchObj.synchronized {
      count = noThreads;
  }
  
  def awaitZero : Unit = {
    synchObj.synchronized{
      while (count > 0) {
        synchObj.wait()
      }
    }
  }
  
  def countDown : Unit = {
    synchObj.synchronized{
      count -= 1
      if(count <= 0){
        synchObj.notifyAll()
      }
    }
  }
}

class JobSlice(l: Latch, index:Int) extends Thread {
  private var latch:Latch = l;
  

  override def run():Unit= {
    try {
      for (i <- 1 to 1000000) {
        if( i % 100000 == 0)
         println(index+": Current value  is "+i)
      }
    } finally {
      latch.countDown;
    }
  }
}

object Latch {
  def main(args: Array[String]): Unit = {
    val noThreads : Int = Integer.valueOf(args(0))
    var l:Latch = new Latch(noThreads);
    for ( i <-  0 to noThreads) {
      val j : Thread = new JobSlice(l,i);
      j.start();
    }
    l.awaitZero
  }
}
