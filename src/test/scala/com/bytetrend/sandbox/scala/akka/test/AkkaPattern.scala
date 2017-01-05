package com.bytetrend.sandbox.scala.akka.test

import akka.actor.{Props, Actor}
import akka.util.Timeout
import com.bytetrend.sandbox.scala.concurrent.GrindingException

import scala.concurrent.duration._
import scala.concurrent.{Future, Promise}
import scala.util.{Random, Success}
import com.bytetrend.sandbox.scala.concurrent._
import com.bytetrend.sandbox.scala.concurrent.CoffeeImplicits.{combine, toCoffeBeans, toMilk}

import scala.concurrent.ExecutionContext.Implicits.global


/**
  * http://danielwestheide.com/blog/2013/01/09/the-neophytes-guide-to-scala-part-8-welcome-to-the-future.html
  */
object CoffeeShop {

  def grind(beans: CoffeeBeans): Promise[GroundCoffee] = {
    val p = Promise[GroundCoffee]()
    Future {
      println("start grinding...")
      Thread.sleep(Random.nextInt(2000))
      import com.bytetrend.sandbox.scala.concurrent.CoffeeImplicits.toCoffeBeans
      if (toCoffeBeans("baked beans") == beans) throw GrindingException("are you joking?")
      println("finished grinding...")
      s"ground coffee of $beans"
    }
    p
  }

  def brew(coffee: GroundCoffee, heatedWater: Water): Promise[Espresso] = {
    val p = Promise[Espresso]()
    Future {
      println("happy brewing :)")
      Thread.sleep(Random.nextInt(2000))
      println("it's brewed!")
      "espresso"
    }
    p
  }


  def heatWater(water: Water): Promise[Water] = {
    val p = Promise[Water]()
    Future {
      println("heating the water now")
      Thread.sleep(2000)
      println("hot, it's hot!")
      water.copy(temperature = 85)
    }
    p
  }

  def frothMilk(milk: Milk): Promise[FrothedMilk] = {
    val p = Promise[FrothedMilk]()
    Future {
      println("milk frothing system engaged!")
      Thread.sleep(Random.nextInt(2000))
      println("shutting down milk frothing system")
      s"frothed $milk"
    }
    p
  }

  def temperatureOkay(water: Water): Future[Boolean] = Future {
    (80 to 85).contains(water.temperature)
  }
}

object AkkaPattern {
  import _root_.com.bytetrend.sandbox.scala.akka.test.CoffeeShop._
  case class EspressoRequest(coffeeType:String , response: Promise[Espresso])
  case class HeatCoffee(data: Promise[GroundCoffee], temperature:Int, response: Promise[Espresso])
  case class BrewCoffee(groundCoffee: Future[GroundCoffee],water: Promise[Water],response:Promise[Espresso])

  class Stage1() extends Actor {

    val nextStage = context.actorOf(Props(new Stage2()))

    override def receive: Receive = {
      case EspressoRequest(coffee, response) =>
        val groundCoffee:Promise[GroundCoffee] = grind(coffee)
        implicit val timeout = Timeout(5 seconds)
 //       groundCoffee.future.map(s => s).pipeTo(sender())
    }
  }

  class Stage2 extends Actor {

    val nextStage = context.actorOf(Props(new Stage3()))

    override def receive: Receive = {
      case HeatCoffee(coffee,temperature, response) =>
        val groundCoffee:Future[GroundCoffee] = for {c <- coffee.future}yield c
        val water:Promise[Water]= heatWater(Water(temperature))
        implicit val timeout = Timeout(5 seconds)
        nextStage ! BrewCoffee(groundCoffee,water, response)
    }
  }

  class Stage3 extends Actor {

    override def receive: Receive = {
      case BrewCoffee(groundCoffee,water, response) =>

  //      val processedData:Espresso = brew(groundCoffee,water)
   //     response.complete(Success(processedData))
    }
  }

}
