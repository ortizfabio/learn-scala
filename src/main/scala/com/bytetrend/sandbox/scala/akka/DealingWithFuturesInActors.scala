package com.bytetrend.sandbox.scala.akka

import akka.actor.{Actor, ActorSystem, Props}
import akka.pattern.PipeToSupport
import com.bytetrend.sandbox.scala.concurrent.CoffeeImplicits._
import com.bytetrend.sandbox.scala.concurrent.CoffeeShop._
import com.bytetrend.sandbox.scala.concurrent._

import scala.concurrent.Await
import scala.concurrent.ExecutionContext.Implicits.global

object CoffeeMaker {
  def props = Props(classOf[CoffeeMaker])

  def name: String = this.getClass.getSimpleName
}


class CoffeeMaker extends Actor with PipeToSupport {

  import context.become

  var coffee: Option[GroundCoffee] = None
  var espresso: Option[Espresso] = None


  override def receive: Receive = readyToGrind

  def readyToGrind: Receive = {
    case beanType: String => {
      become(prepareWater)
      grind(CoffeeBeans(beanType)).pipeTo(self)(sender())
    }

  }

  def prepareWater: Receive = {
    case groundCoffee: GroundCoffee => {
      coffee = Some(groundCoffee)
      become(brewCoffee)
      heatWater(Water(120)).pipeTo(self)(sender())
    }
  }

  def brewCoffee: Receive = {
    case water: Water => {
      become(prepareMilk)
      brew(coffee.get, water).pipeTo(self)(sender())
    }
  }

  def prepareMilk: Receive = {
    case coffee: Espresso => {
      espresso = Some(coffee)
      become(barista)
      frothMilk(Milk("non-fat")).pipeTo(self)(sender())
    }
  }

  def barista: Receive = {
    case milk: FrothedMilk => {
      val snd = sender()
      snd ! combine(espresso.get, milk)
    }
  }
}

/**
  * EchoActor sends back received messages (unmodified).
  */
class EchoActor extends Actor {
  override def receive = {
    case message ⇒ sender() ! message
  }
}

/**
  *
  */
object DealingWithFuturesInActors extends App {

  import akka.pattern.ask
  import akka.util.Timeout

  import scala.concurrent.duration._


  val system = ActorSystem("Coffee-Shop")
  val coffeeShop = system.actorOf(CoffeeMaker.props, CoffeeMaker.name)
  implicit val timeout: Timeout = Timeout(18 seconds)
  implicit val echo = system.actorOf(Props(new EchoActor), "echo")
  val coffee = Await.result(coffeeShop ? ("Arabica"), timeout.duration)
  println(coffee)

  System.exit(0)
}