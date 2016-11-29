package com.bytetrend.sandbox.scala.akka.test

import _root_.akka.actor.{ActorRef, Props, ActorSystem}
import _root_.akka.testkit.{ImplicitSender, TestKit}
import _root_.akka.util.Timeout
import com.bytetrend.sandbox.scala.akka
import java.util.concurrent.TimeUnit._

import com.bytetrend.sandbox.scala.akka.{Supervisor, Child}
import com.typesafe.config.ConfigFactory
import org.scalatest.{BeforeAndAfterAll, FlatSpecLike, Matchers}

import scala.concurrent.duration.Duration

class SupervisorFaultHandlingDocSpec(_system: ActorSystem) extends TestKit(_system)
  with ImplicitSender with FlatSpecLike with Matchers with BeforeAndAfterAll {

  def this() = this(ActorSystem(
    "FaultHandlingDocSpec",
    ConfigFactory.parseString(
      """
      akka {
        loggers = ["akka.testkit.TestEventListener"]
        loglevel = "WARNING"
      }
      """)))

  override def afterAll {
    TestKit.shutdownActorSystem(system)
  }

  "A supervisor" must "apply the chosen strategy for its child" in {
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

