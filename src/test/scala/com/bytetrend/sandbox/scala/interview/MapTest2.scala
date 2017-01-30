package com.bytetrend.sandbox.scala.interview


import com.bytetrend.sandbox.scala.interview.MapImplementation.map
import org.junit.Test
import org.scalacheck.Arbitrary._
import org.scalacheck.Prop._
import org.scalatest.junit.JUnitSuite
import org.scalatest.prop.Checkers

/**
  * Test map function using scalacheck to generate 2 test with lists of
  * 10,000,000 elements. The result of the custom map function should be the
  * same as the Scala original map function.
  */
class MapTest2 extends JUnitSuite with Checkers {
  @Test
  def testConcat() {
    implicit  val generatorDrivenConfig =PropertyCheckConfig(minSize = 10000000,
      maxSize = 10000000,minSuccessful = 2 )
    check((a: List[Int]) => map(a, (x:Int) => 2 * x) == a.map(x => 2 * x))
  }
}
