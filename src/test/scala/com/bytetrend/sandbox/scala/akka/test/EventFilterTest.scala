package com.bytetrend.sandbox.scala.akka.test

import akka.actor.{Actor, ActorLogging, ActorSystem}
import akka.testkit.{EventFilter, TestActorRef, TestKit}
import com.typesafe.config.ConfigFactory
import org.scalatest.{BeforeAndAfterAll, OneInstancePerTest, WordSpecLike}

import scala.concurrent.duration._

class MyTestActor extends Actor with ActorLogging {
  import context.dispatcher
  def receive = {
    case 'Crash => scheduler.scheduleOnce(100.millis)(log.error("Crash"))
  }
  def scheduler = context.system.scheduler
}

/**
  * http://techblog.net-a-porter.com/2014/01/safer-testing-with-akka-eventfilter/
  */
class FailingTest extends TestKit(ActorSystem("MyActorTest", ConfigFactory.parseString("""
  akka.loggers = ["akka.testkit.TestEventListener"]
  """)))
    with WordSpecLike
    with BeforeAndAfterAll
    with OneInstancePerTest {

  override def afterAll() {
    system.terminate()
  }
/*
  "MyActor" should {
    "crash twice" in {
      val actor = TestActorRef(new MyTestActor)
      actor ! 'Crash
      actor ! 'Crash
    }
*/
    "crash once" in {
      val actor = TestActorRef(new MyTestActor)
      EventFilter.error(source= "akka://MyActorTest/user/$$a",message = "Crash", occurrences = 1) intercept {
        actor ! 'Crash
      }
    }

}
