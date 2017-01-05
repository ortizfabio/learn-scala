package com.bytetrend.sandbox.scala.akka.test

import java.util.concurrent.TimeUnit._

import akka.actor.{ActorRef, Props}
import akka.util.Timeout
import com.bytetrend.sandbox.scala.akka.{Child, Supervisor}
import org.specs2.execute.AsResult
import org.specs2.mutable.Specification

import scala.concurrent.duration.Duration

/**
  * Created by jose.ortiz on 11/18/16.
  */
class SupervisorFaultHandlerSpecification extends Specification {
  sequential

  override def is = sequential ^
    s2"""
       | Given a supervisory strategy when i send a valid message
       | I should expect a message to be received. $receiveValidMessage
    """

  def receiveValidMessage = {
    new AkkaTestkitSpecs2Support {
      implicit val executionContext = system.dispatcher
      implicit val timeout: Timeout = new Timeout(Duration.create(15, SECONDS))
      val supervisor = system.actorOf(Props(classOf[Supervisor]), "supervisor")

      supervisor ! Props[Child]
      val child = expectMsgType[ActorRef] // retrieve answer from TestKit’s testActor

      child ! 42 // set state to 42
      child ! "get"
      expectMsg(42)

      child ! new ArithmeticException // crash it
      child ! "get"
      expectMsg(42)
    }
  }

}
