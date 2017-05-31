package com.bytetrend.sandbox.scala.akka


import akka.actor.{Actor, ActorRef, ActorSystem, Props}
import akka.pattern.{PipeToSupport, ask}
import akka.util.Timeout
import com.bytetrend.sandbox.scala.akka.MapUpdater.{Get, Store}

import scala.concurrent.Await
import scala.concurrent.duration._


object MapUpdater {
  def props = Props(classOf[MapUpdater])

  def name: String = this.getClass.getSimpleName

  case class Store(p: (Int, Int))

  case object Get

}

/**
  * http://stackoverflow.com/questions/6867702/updating-scala-collections-thread-safely
  *
  */
class MapUpdater extends Actor {
  private var x = Map[Int, Int]()

  def receive = {
    case Store(p) => x = x + p
    case Get => sender() ! x
  }
}

object Printer {
  def props = Props(classOf[Printer])

  def name: String = this.getClass.getSimpleName
}

/**
  * PrintActor prints message received.
  */
class Printer extends Actor {
  override def receive = {
    case message: Any ⇒ println(message)
  }
}


/**
  *
  *
  */
object ActorMapUpdater extends App with PipeToSupport {

  import scala.concurrent.ExecutionContext.Implicits.global

  def scheduleShutdown() {
    //https://groups.google.com/forum/#!msg/akka-user/rDaa9IkfzVw/txajmuKCS0oJ
    System.err.println("Scheduling shutdown")
    system.scheduler.scheduleOnce(Duration(10, SECONDS), new Runnable() {
      override def run() {
        system.terminate()
        System.err.println("shutdown")
      }
    }
    )

  }

  val system: ActorSystem = ActorSystem("collections-thread-safely")
  val ref: ActorRef = system.actorOf(MapUpdater.props, MapUpdater.name)
  system.actorOf(Props[Printer], Printer.name)
  implicit val resolveTimeout = Timeout(5 seconds)

  val mapUpdaterActor = Await.result(system.actorSelection(s"user/${MapUpdater.name}").resolveOne(), resolveTimeout.duration)
  val printerActor = Await.result(system.actorSelection(s"user/${Printer.name}").resolveOne(), resolveTimeout.duration)
  mapUpdaterActor ! Store((3, 9))
  mapUpdaterActor ! Store((1, 4))
  mapUpdaterActor ! Store((2, 3))

  //This blocking call is replaced with the pipeTo call
  //val m:Future[Map[Int, Int]] = (actorRef ? Get).asInstanceOf[Future[Map[Int, Int]]]
  //m.onSuccess{ case x : Map[Int, Int] => println( x) }
  (mapUpdaterActor ? Get).pipeTo(printerActor)

  mapUpdaterActor ! Store((2, 3))
  scheduleShutdown

}

import java.util.concurrent.atomic.AtomicReference

trait ReferenceUpdate[T] {

  def compareAndSetSync(ref: AtomicReference[T])(logic: (T => T)) {
    while (true) {
      val snapshot = ref.get
      val update = logic(snapshot)
      if (ref.compareAndSet(snapshot, update)) return
    }
  }
}

/**
  * http://stackoverflow.com/questions/6867702/updating-scala-collections-thread-safely
  *
  */
class ReferenceMapUpdater extends ReferenceUpdate[Map[Int, Int]] {
  private val x = new AtomicReference(Map[Int, Int]())

  def update(p: (Int, Int)) {
    compareAndSetSync(x)(y => y + p)

    /* Replaced this code by the method above.
       while (true) {
        val oldMap = x.get // get old value
        val newMap = oldMap + p // update
        if (x.compareAndSet(oldMap, newMap))
          return // exit if update was successful, else repeat
      }
     */
  }

  def get = x.get
}

object TestReferenceUpdater extends App {

  val updater = new ReferenceMapUpdater

  updater.update((3, 9))
  updater.update((1, 4))
  updater.update((2, 3))
  println(updater.get)
}