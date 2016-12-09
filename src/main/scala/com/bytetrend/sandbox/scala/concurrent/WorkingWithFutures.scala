package com.bytetrend.sandbox.scala.concurrent

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
import scala.concurrent.{Await, Future}
import scala.language.postfixOps
import scala.util.{Success, Failure, Random}

case class CoffeeBeans(brand: String)

case class GroundCoffee(message: String)

case class GrindingException(reason: String) extends Exception(reason)

case class Water(temperature: Int)

case class Milk(pctOfFat: String)

case class FrothedMilk(foam: String)

case class Espresso(roast: String)

case class Cappuccino(espresso:Espresso, foam: FrothedMilk )

object CoffeeImplicits {

  implicit def toGroundCoffee(m: String) = GroundCoffee(m)

  implicit def toCoffeBeans(s: String) = CoffeeBeans(s)

  implicit def toMilk(m: String) = Milk(m)

  implicit def toFrothedMilk(m: String) = FrothedMilk(m)

  implicit def toEspresso(m: String) = Espresso(m)

  implicit def toWater(t: Int) = Water(t)

  implicit def combine(espresso: Espresso, frothedMilk: FrothedMilk) = Cappuccino(espresso,frothedMilk)
}

/**
  * http://danielwestheide.com/blog/2013/01/09/the-neophytes-guide-to-scala-part-8-welcome-to-the-future.html
  */
object CoffeeShop {

  def grind(beans: CoffeeBeans): Future[GroundCoffee] = Future {
    println("start grinding...")
    Thread.sleep(Random.nextInt(2000))
    import com.bytetrend.sandbox.scala.concurrent.CoffeeImplicits.toCoffeBeans
    if (toCoffeBeans("baked beans") == beans) throw GrindingException("are you joking?")
    println("finished grinding...")
    import com.bytetrend.sandbox.scala.concurrent.CoffeeImplicits.toGroundCoffee
    s"ground coffee of $beans"
  }

  def brew(coffee: GroundCoffee, heatedWater: Water): Future[Espresso] = Future {
    println("happy brewing :)")
    Thread.sleep(Random.nextInt(2000))
    println("it's brewed!")
    import com.bytetrend.sandbox.scala.concurrent.CoffeeImplicits.toEspresso
    "espresso"
  }


  def heatWater(water: Water): Future[Water] = Future {
    println("heating the water now")
    Thread.sleep(2000)
    println("hot, it's hot!")
    water.copy(temperature = 85)
  }

  def frothMilk(milk: Milk): Future[FrothedMilk] = Future {
    println("milk frothing system engaged!")
    Thread.sleep(Random.nextInt(2000))
    println("shutting down milk frothing system")
    import com.bytetrend.sandbox.scala.concurrent.CoffeeImplicits.toFrothedMilk
    s"frothed $milk"
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
    import com.bytetrend.sandbox.scala.concurrent.CoffeeImplicits.toMilk
    import com.bytetrend.sandbox.scala.concurrent.CoffeeImplicits.combine
    import com.bytetrend.sandbox.scala.concurrent.CoffeeImplicits.toCoffeBeans
    for {
      ground <- grind("arabica beans")
      water <- heatWater(Water(20))
      foam <- frothMilk("milk")
      espresso <- brew(ground, water)
    } yield combine(espresso, foam)
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
    * @return
    */
  def prepareCappuccino(): Future[Cappuccino] = {
    import com.bytetrend.sandbox.scala.concurrent.CoffeeImplicits.toMilk
    import com.bytetrend.sandbox.scala.concurrent.CoffeeImplicits.combine
    import com.bytetrend.sandbox.scala.concurrent.CoffeeImplicits.toCoffeBeans
    val groundCoffee = grind("arabica beans")
    val heatedWater = heatWater(Water(20))
    val frothedMilk = frothMilk("milk")
    for {
      ground <- groundCoffee
      water <- heatedWater
      foam <- frothedMilk
      espresso <- brew(ground, water)
    } yield combine(espresso, foam)
  }

}

object FuturePromiseExample extends App {

  import com.bytetrend.sandbox.scala.concurrent.CoffeeShop._

  /**
    * This would not print unless a wait is added after it.
    * grind("arabica beans").onSuccess {
    * case ground => println("okay, got my ground coffee")
    * }
    * import com.bytetrend.sandbox.scala.concurrent.CoffeeImplicits.toCoffeBeans
    * val future = grind("baked beans").map{coffee =>
    * println(s"okay, got my ground coffee $coffee")
    * }
    * Await.ready(future, 2 seconds)

  val waterAt25:Future[Boolean] = heatWater(Water(25)).map { water =>
    println("we're in the future!")
    (80 to 85).contains(water.temperature)
  }
   Await.ready(waterAt25, 2 seconds)
 waterAt25.map( x => println(x.toString) )

    import com.bytetrend.sandbox.scala.concurrent.CoffeeImplicits.toCoffeBeans
    val coffeeResult = grind("baked beans")
    coffeeResult.onComplete {
      case Success(ground) => {
        println(s"got my $ground")
      }
      case Failure(ex) => {
        println("This grinder needs a replacement, seriously!")
      }
    }
    Await.ready(coffeeResult, 3 seconds)

    */

  /*
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
    Await.ready(waterOk,5 seconds)
    waterOk.map(s => println("mapping result "+s))
    */
    def temperatureOkay(water: Water): Future[Boolean] = Future {
      (80 to 85).contains(water.temperature)
    }
/*
    val nestedFuture: Future[Future[Boolean]] = heatWater(Water(25)).map {
      water => temperatureOkay(water)
    }
    val flatFuture: Future[Boolean] = heatWater(Water(25)).flatMap {
      water => temperatureOkay(water)
    }
    val acceptable: Future[Boolean] = for {
      heatedWater <- heatWater(Water(25))
      okay <- temperatureOkay(heatedWater)
    } yield okay

    Await.ready(acceptable,5 seconds)
    acceptable.foreach( x => println(x) )

    val cappuccino:Future[Cappuccino] = prepareCappuccinoSequentially()
    Await.ready(cappuccino,5 seconds)
    cappuccino.foreach(println)
*/

    val cappuccino:Future[Cappuccino] = prepareCappuccino()
    Await.ready(cappuccino,5 seconds)
    cappuccino.foreach(println)
  }


