package com.bytetrend.sandbox.scala.algo

import org.scalacheck.Gen
import org.scalatest.prop.GeneratorDrivenPropertyChecks
import org.scalatest.{FunSpec, Matchers}

/**
  * This is a test for <code>com.bytetrend.sandbox.scala.algo.PrimeFactors</code>
  *
  */
class PrimeFactorsTest extends FunSpec with Matchers with PrimeFactors with GeneratorDrivenPropertyChecks {
  //private object testPrimeFactor extends PrimeFactors
  describe("The  factors function should return a map with the prime factors of n") {

    it("When the prime factors returned by primeFactor(600) are multiply they should equal 600 ") {
      val primes = factors(600)
      primes.foldLeft(1)((x, y) => x * (Math.pow(y._1, y._2).toInt)) shouldBe 600
    }

    it("Run with random generated numbers from 2 to 1024") {
      forAll(Gen.choose(2, 1024)) {
        (a: Int) =>
          val factorMap = factors(a)
          val b:Int = factorMap.foldLeft(1)((x, y) => x * (Math.pow(y._1, y._2).toInt))
          b should be(a)
      }
    }
  }

}
