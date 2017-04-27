package com.bytetrend.sandbox.scala.akka.transactor

import akka.actor.{ActorSystem, Props}
import akka.pattern.ask
import akka.transactor.Coordinated
import akka.util.Timeout

import scala.concurrent.Await
import scala.concurrent.duration._

object Main extends App {
  val system = ActorSystem("app")

  val counter1 = system.actorOf(Props[Counter], name = "counter1")
  val counter2 = system.actorOf(Props[Counter], name = "counter2")

  implicit val timeout = Timeout(5 seconds)

  counter1 ! Coordinated(Increment(Some(counter2)))

  val count = Await.result(counter1 ? GetCount, timeout.duration)

   count == 1


}
