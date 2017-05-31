package com.bytetrend.sandbox.scala.collection

import java.util.concurrent.{ExecutorService, Executors}

import scala.concurrent.ExecutionException

/**
 * Created by db2admin on 5/13/2016.
 */
object TryCatch {

  def main(args: Array[String]): Unit = {

    import java.util.HashSet

    val set: ObservableSet[Int] = new ObservableSet[Int](new HashSet[Int]())

    set.addObserver(new SetObserver[Int] {
      def added(s: ObservableSet[Int], e: Int): Unit = {
        println(e)

        if (e == 23) {
          val executor: ExecutorService = Executors.newSingleThreadExecutor()
          val observer: SetObserver[Int] = this;
          try {
            executor.submit(new Runnable() {
              def run: Unit = s.removeObserver(observer)
            }).get()
          } catch {
            case t: ExecutionException => throw new AssertionError(t.getCause())
            case t: InterruptedException => throw new AssertionError(t.getCause())
          } finally {
            executor.shutdown()
          }
        }
      }
    })

    for (i <- 0 to 99)
      set.add(i)

  }

}
