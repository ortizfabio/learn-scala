package com.bytetrend.sandbox.scala.concurrent

import scala.concurrent.{Await, Promise, Future}
import scala.util.{Success, Failure}
//import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._

import java.util.concurrent.Executors
import concurrent.ExecutionContext

object Government extends App{
  val executorService = Executors.newFixedThreadPool(4)
  implicit val executionContext = ExecutionContext.fromExecutorService(executorService)

  case class TaxCut(reduction: Int)
  def redeemCampaignPledge(): Future[TaxCut] = {
    val p = Promise[TaxCut]()
    Future {
      println("Starting the new legislative period.")
      Thread.sleep(2000)
      p.success(TaxCut(20))
      println("We reduced the taxes! You must reelect us!!!!1111")
    }
    p.future
  }

  val taxCutF: Future[TaxCut] = Government.redeemCampaignPledge()
  println("Now that they're elected, let's see if they remember their promises...")
  taxCutF.onComplete {
    case Success(TaxCut(reduction)) =>
      println(s"A miracle! They really cut our taxes by $reduction percentage points!")
    case Failure(ex) =>
      println(s"They broke their promises! Again! Because of a ${ex.getMessage}")
  }
  Await.ready(taxCutF, 5 seconds)
  println("The end")
}
