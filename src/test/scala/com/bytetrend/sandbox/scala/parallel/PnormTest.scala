package com.bytetrend.sandbox.scala.parallel

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.prop.Checkers
import org.scalatest.{FunSuite, ShouldMatchers}

@RunWith(classOf[JUnitRunner])
class PnormTest extends FunSuite with ShouldMatchers with Checkers  {


  test("p-norm sequential vs parallel"){
      check(PnormChecker)
  }
}
