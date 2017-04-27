package com.bytetrend.sandbox.scala.akka


import akka.actor.{Actor, ActorSystem, Props}
import akka.util.Timeout

import scala.concurrent.Await
import scala.concurrent.duration._

object SomeActor {

  case object Ping

}

class SomeActor extends Actor {

  import SomeActor._

  def receive = {
    case Ping =>
      println("Pong")
  }
}

object ActorSelection extends App {
  val system: ActorSystem = ActorSystem("test-system")

  system.actorOf(Props[SomeActor], "test-actor")

  implicit val resolveTimeout = Timeout(5 seconds)

  val actorRef = Await.result(system.actorSelection("user/test-actor").resolveOne(), resolveTimeout.duration)

  actorRef ! SomeActor.Ping
}


