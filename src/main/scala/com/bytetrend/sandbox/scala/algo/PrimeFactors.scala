package com.bytetrend.sandbox.scala.algo

/**
  * It calculates all the factors on an integer and returns a map
  * where the keys are the factors and the values are the multiplicity of
  * such factor.
  * Ex:
  * When number is 155 returns Map(5 -> 1, 31 -> 1)
  * When number is 512 returns Map(2 -> 9)
  * When number is 600 returns Map(2 -> 3, 5 -> 2, 3 -> 1)
  * When number is 29 returns Map(29 -> 1)
  *
  * @see com.bytetrend.sandbox.scala.algo.PrimeFactorsTest for an automated test.
  */
trait PrimeFactors {

  def factors(num: Long): Map[Long, Int] = {
    /**
      * Collect all factors using a map where each element of the map
      * is a factor (key) and its multiplicity (value)
      */
    val factors = collection.mutable.Map[Long, Int]()

    /**
      * This auxiliary function expects a number with no even
      * factors. Then it factors out the odd factors including
      * prime numbers.
      *
      * @param num
      */
    def oddFactors(num: Long): Unit = {
      var n = num
      val limit: Int = Math.sqrt(n).toInt
      // n must be odd at this point.  So we can
      // skip one element (Note i = i +2)
      for (i <- 3 to limit by 2) {
        while (n % i == 0) {
          factors(i) = factors.getOrElseUpdate(i, 0) + 1
          n /= i
        }
      }
      //If n greater than 1 it is a prime number that did not
      //got calculated to its fullest factors and needs to be saved.
      //for example 155 has 5^1 * 31^1 but the sqrt(155) = 12
      // so 31 would not be saved except by this. Also a prime number
      //would have no factors and it would need to be saved here.
      if (n > 1)
        factors += (n -> 1)
    }

    /**
      * Even factors should be calculated first.
      *
      * @param num
      * @return return residual integer after even factors are factored out.
      */
    def evenFactors(num: Long): Long = {
      var n = num
      while (n % 2 == 0) {
        factors(2) = factors.getOrElseUpdate(2, 0) + 1
        n /= 2
      }
      n
    }

    oddFactors(evenFactors(num))
    factors.toMap
  }

}

object TestPrimeFactor extends App {

  /**
    * Create an instance of
    */
  object testPrimeFactor extends PrimeFactors

  println(s"prime factors of 155 are $testPrimeFactor.primeFactors(155)")

  println(s"prime factors of 512 are $testPrimeFactor.primeFactors(512)")

  println(s"prime factors of 600 are $testPrimeFactor.primeFactors(600)")

  println(s"prime factors of 29 are $testPrimeFactor.primeFactors(29)")

}