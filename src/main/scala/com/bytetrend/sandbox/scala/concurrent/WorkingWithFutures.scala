package com.bytetrend.sandbox.scala.concurrent

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
import scala.concurrent.{Await, Future}
import scala.language.postfixOps
import scala.util.{Failure, Random, Success}

case class CoffeeBeans(brand: String)

case class GroundCoffee(message: String)

case class GrindingException(reason: String) extends Exception(reason)

case class Water(temperature: Int)

case class Milk(pctOfFat: String)

case class FrothedMilk(foam: String)

case class Espresso(roast: String)

case class Cappuccino(espresso: Espresso, foam: FrothedMilk)

object CoffeeImplicits {

  implicit def toGroundCoffee(m: String) = GroundCoffee(m)

  implicit def toCoffeBeans(s: String) = CoffeeBeans(s)

  implicit def toMilk(m: String) = Milk(m)

  implicit def toFrothedMilk(m: String) = FrothedMilk(m)

  implicit def toEspresso(m: String) = Espresso(m)

  implicit def toWater(t: Int) = Water(t)

  implicit def combine(espresso: Espresso, frothedMilk: FrothedMilk) = Cappuccino(espresso, frothedMilk)
}

/**
  * http://danielwestheide.com/blog/2013/01/09/the-neophytes-guide-to-scala-part-8-welcome-to-the-future.html
  */
object CoffeeShop {

  def temperatureOkay(water: Water): Future[Boolean] = Future {
    (80 to 85).contains(water.temperature)
  }

  /**
    * This reads nicely, but since a for comprehension is just another representation
    * for nested flatMap calls, this means that the Future[Water] created in
    * heatWater is only really instantiated after the Future[GroundCoffee]
    * has completed successfully. You can check this by watching the sequential
    * console output coming from the functions we implemented above.
    *
    * @return
    */
  def prepareCappuccinoSequentially(): Future[Cappuccino] = {

    import com.bytetrend.sandbox.scala.concurrent.CoffeeImplicits.{combine, toCoffeBeans, toMilk}

    for {
      ground <- grind("arabica beans")
      water <- heatWater(Water(20))
      foam <- frothMilk("milk")
      espresso <- brew(ground, water)
    } yield combine(espresso, foam)
  }

  def grind(beans: CoffeeBeans): Future[GroundCoffee] = {
    val f = Future[GroundCoffee] {
      println("start grinding...")
      Thread.sleep(Random.nextInt(2000))
      import com.bytetrend.sandbox.scala.concurrent.CoffeeImplicits.toCoffeBeans
      if (toCoffeBeans("baked beans") == beans) throw GrindingException("are you joking?")
      println("finished grinding...")
      import com.bytetrend.sandbox.scala.concurrent.CoffeeImplicits.toGroundCoffee
      s"ground coffee of $beans"
    }
    f
  }

  def brew(coffee: GroundCoffee, heatedWater: Water): Future[Espresso] = {
    val f = Future[Espresso] {
      println("happy brewing :)")
      Thread.sleep(Random.nextInt(2000))
      println("it's brewed!")
      import com.bytetrend.sandbox.scala.concurrent.CoffeeImplicits.toEspresso
      "espresso"
    }
    f
  }

  def heatWater(water: Water): Future[Water] = {
    val f = Future[Water] {
      println("heating the water now")
      Thread.sleep(2000)
      println("hot, it's hot!")
      water.copy(temperature = 85)
    }
    f
  }

  def frothMilk(milk: Milk): Future[FrothedMilk] = {
    val f = Future[FrothedMilk] {
      println("milk frothing system engaged!")
      Thread.sleep(Random.nextInt(2000))
      println("shutting down milk frothing system")

      import com.bytetrend.sandbox.scala.concurrent.CoffeeImplicits.toFrothedMilk

      s"frothed $milk"
    }
    f
  }

  /**
    * Hence, make sure to instantiate all your independent futures before the for comprehension
    * Now, the three futures we create before the for comprehension start being
    * completed immediately and execute concurrently. If you watch the console
    * output, you will see that it’s non-deterministic. The only thing that’s
    * certain is that the "happy brewing" output will come last. Since the
    * method in which it is called requires the values coming from two other
    * futures, it is only created inside our for comprehension, i.e. after
    * those futures have completed successfully.
    *
    * @return
    */
  def prepareCappuccino(): Future[Cappuccino] = {

    import com.bytetrend.sandbox.scala.concurrent.CoffeeImplicits.{combine, toCoffeBeans, toMilk}

    val groundCoffee: Future[GroundCoffee] = grind("arabica beans")
    val heatedWater: Future[Water] = heatWater(Water(20))
    val frothedMilk: Future[FrothedMilk] = frothMilk("milk")
    val f: Future[Cappuccino] = for {
      ground <- groundCoffee
      water <- heatedWater
      foam <- frothedMilk
      espresso <- brew(ground, water)
    } yield combine(espresso, foam)
    f
  }

}

object FutureBasicExample extends App {

  import com.bytetrend.sandbox.scala.concurrent.CoffeeShop._


  val waterOk: Future[Boolean] = heatWater(Water(25)).map { water =>
    println("we're in the future!")
    (80 to 85).contains(water.temperature)
  }
  waterOk.onComplete {
    case Success(water) => {
      println(s"water was heated at $water")
    }
    case Failure(ex) => {
      println(s"No hot tea today! because ${ex}")
    }
  }
  Await.ready(waterOk, 5 seconds)
  waterOk.map(s => println("mapping result " + s))

}

object FutureSequentially extends App {

  import com.bytetrend.sandbox.scala.concurrent.CoffeeShop._

  val waterOk: Future[Boolean] = heatWater(Water(25)).map { water =>
    println("we're in the future!")
    (80 to 85).contains(water.temperature)
  }
  waterOk.onComplete {
    case Success(water) => {
      println(s"water was heated ")
    }
    case Failure(ex) => {
      println("No hot tea today!")
    }
  }
  Await.ready(waterOk, 5 seconds)
  waterOk.map(s => println("mapping result " + s))
}

object FutureInParallel extends App {

  import com.bytetrend.sandbox.scala.concurrent.CoffeeShop._


  val cappuccino: Future[Cappuccino] = prepareCappuccino()
  Await.ready(cappuccino, 5 seconds)
  cappuccino.foreach(println)
}

object SumOfFutures extends App {

  import scala.concurrent.duration._
  import scala.concurrent.{Await, ExecutionContext, Future}
  import ExecutionContext.Implicits.global

  val f1 = Future {
    Thread.sleep(10000)
    1
  }
  val f2 = Future {
    Thread.sleep(10000)
    2
  }
  val f3 = for {
    v1 <- f1
    v2 <- f2
  } yield v1 + v2
  println(Await.result(f3, 30.second))
}

object UsingFilter extends App {
  val f = Future {
    5
  }
  val g = f filter {
    _ % 2 == 1
  }
  val h = f filter {
    _ % 2 == 0
  }
  val x = Await.result(g, Duration.Zero) // evaluates to 5
  println(s"value is $x")

  try {
    val t = Await.result(h, Duration.Zero) // throw a NoSuchElementException
    println(s" value $t")
  } catch {
    case e: NoSuchElementException => println(s"Exception $e")
    case t:Throwable => println(s"some exception $t")
  }


}