package com.bytetrend.sandbox.scala.akka.test


import akka.actor.{ActorKilledException, ActorSystem, Kill, Props}
import akka.testkit.{TestKitBase, TestProbe, EventFilter}
import com.typesafe.config.ConfigFactory
import org.specs2.matcher.MatchResult
import org.specs2.mutable.{ Specification}
import org.specs2.specification.After


import org.specs2.time.{NoTimeConversions}

class ExpectLogMessageSpecificatio extends Specification with After with TestKitBase{

  override def is = s2"""
       | Given a supervisory strategy when i send a valid message
       | I should expect a message to be received. $receiveValidMessage
    """

  implicit lazy val system = ActorSystem("testsystem", ConfigFactory.parseString("""
  akka.loggers = ["akka.testkit.TestEventListener"]
                                                                   """))

  def receiveValidMessage:MatchResult[Any] = {
      import scala.concurrent.duration.{Duration,SECONDS}
      val duration = Duration(5, SECONDS)

       within( duration) {
        val actor = system.actorOf(Props.empty)
        EventFilter[ActorKilledException](occurrences = 1) intercept {
          actor ! Kill
        }
      }
    1 === 1
  }

   override def after: Any = system.terminate

//  override protected def durationIn(unit: duration.TimeUnit): FiniteDuration = FiniteDuration(5,TimeUnit.SECONDS)

}