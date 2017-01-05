package com.bytetrend.sandbox.scala.akka.test


import org.scalatest.{Suite, BeforeAndAfterAll}
import akka.testkit.TestKit

trait StopSystemAfterAll extends BeforeAndAfterAll {
  this: TestKit with Suite =>
  override protected def afterAll(): Unit = {
    super.afterAll()
    system.terminate()
  }
}
