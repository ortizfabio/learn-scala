package com.bytetrend.sandbox.scala.akka

import akka.actor.{Actor, ActorLogging, ActorSystem, Props}
import akka.pattern.PipeToSupport
import com.bytetrend.sandbox.scala.akka.CoffeeMaker.{CoffeeAndWater, EspressoAndFrothMilk}
import com.bytetrend.sandbox.scala.concurrent.CoffeeImplicits._
import com.bytetrend.sandbox.scala.concurrent.CoffeeShop._
import com.bytetrend.sandbox.scala.concurrent._

import scala.concurrent.ExecutionContext.Implicits.global

object CoffeeMaker {
  def props = Props(classOf[CoffeeMaker])

  def name: String = this.getClass.getSimpleName

  case class CoffeeAndWater(groundCoffee: Option[GroundCoffee], boilingWater: Option[Water])

  case class EspressoAndFrothMilk(espresso: Option[Espresso], milk: Some[FrothedMilk])

}

trait Messages {
  lazy val unknownMessageReceived = "Received unknown message: {} in method {}"
}

class CoffeeMaker extends Actor with PipeToSupport with ActorLogging with Messages {

  import context.become

  override def receive: Receive = readyToGrind

  def readyToGrind: Receive = {
    case beanType: String => {
      become(prepareWater)
      grind(CoffeeBeans(beanType)).pipeTo(self)(sender())
    }
    case x => log.error(unknownMessageReceived, x, "readyToGrind")
  }

  def prepareWater: Receive = {
    case groundCoffee: GroundCoffee => {
      val coffee: Option[GroundCoffee] = Some(groundCoffee)
      become(brewCoffee)
      heatWater(Water(120)).map(water => CoffeeAndWater(coffee, Some(water))).pipeTo(self)(sender())
    }
    case x => log.error(unknownMessageReceived, x, "prepareWater")
  }

  def brewCoffee: Receive = {
    case coffeeAndWater: CoffeeAndWater => {
      become(prepareMilk)
      brew(coffeeAndWater.groundCoffee.get, coffeeAndWater.boilingWater.get).pipeTo(self)(sender())
    }
    case x => log.error(unknownMessageReceived, x, "brewCoffee")
  }

  def prepareMilk: Receive = {
    case coffee: Espresso => {
      val espresso = Some(coffee)
      become(done)
      frothMilk(Milk("non-fat")).map(m => EspressoAndFrothMilk(espresso, Some(m))).pipeTo(self)(sender())
    }
    case x => log.error(unknownMessageReceived, x, "prepareMilk")
  }

  def done: Receive = {
    case ingredients: EspressoAndFrothMilk => {
      val snd = sender()
      snd ! combine(ingredients.espresso.get, ingredients.milk.get)
      become(readyToGrind)
    }
    case x => log.error(unknownMessageReceived, x, "done")
  }
}

object Customer {
  def props = Props(classOf[Customer])

  def name: String = this.getClass.getSimpleName
}

class Customer extends Actor with ActorLogging with Messages {
  override def receive = {

    case beverage: Cappuccino ⇒ {
      val who = sender()
      log.debug("Cappuccino ready " + beverage)
    }
    case x => log.error(unknownMessageReceived, x, "receive")
  }
}

/**
  *
  */
object DealingWithFuturesInActors extends App with PipeToSupport {

  import akka.pattern.ask
  import akka.util.Timeout
  import com.typesafe.config.ConfigFactory

  import scala.concurrent.duration._

  val config = ConfigFactory.load("dealing-with-futures")
  val system = ActorSystem("Coffee-Shop", config)
  val coffeeShop = system.actorOf(CoffeeMaker.props, CoffeeMaker.name)
  implicit val timeout: Timeout = Timeout(18 seconds)
  implicit val customer = system.actorOf(Customer.props, Customer.name)
  (coffeeShop ? ("Arabica")).pipeTo(customer)
  //  val coffee = Await.result(coffeeShop ? ("Arabica"), timeout.duration)
  //  println(coffee)
  scheduleShutdown()
  // System.exit(0)

  def scheduleShutdown() {
    //https://groups.google.com/forum/#!msg/akka-user/rDaa9IkfzVw/txajmuKCS0oJ
    System.err.println("Scheduling shutdown")
    system.scheduler.scheduleOnce(Duration(30, SECONDS), new Runnable() {
      override def run() {
        system.terminate()
        System.err.println("shutdown")
      }
    }
    )

  }
}