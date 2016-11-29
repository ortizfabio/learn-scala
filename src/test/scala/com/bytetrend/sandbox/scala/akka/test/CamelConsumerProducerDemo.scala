package com.bytetrend.sandbox.scala.akka.test

import akka.actor.{ActorRef, Actor, Props, ActorSystem}
import akka.camel.{CamelExtension, CamelMessage, Consumer}
import org.apache.camel.builder.RouteBuilder
import akka.camel._

object Frontend {
  def main(args: Array[String]) {
    val system = ActorSystem("system")
    val actor = system.actorOf(Props[ConsumerActor], "myconsumer")

    val context = CamelExtension(system).context
    context.addRoutes(new RouteBuilder() {
      override def configure(): Unit = {
        from("direct:start")
          .to("akka://system/user/myconsumer")
      }
    })

    Thread.sleep(5 * 1000)

    val producer = context.createProducerTemplate()
    producer.sendBody("direct:start", "HELLO!")
    Thread.sleep(10 * 1000)
  }
}

class ConsumerActor extends Actor {
  override def receive = {
    case _ => println("OK")
  }
}
