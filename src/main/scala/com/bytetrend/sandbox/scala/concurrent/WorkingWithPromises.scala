package com.bytetrend.sandbox.scala.concurrent

import java.io.File
import java.util.Properties

import scala.concurrent.{Await, Future, Promise}
import scala.util.{Failure, Success}
import scala.concurrent.duration._
import java.util.concurrent.Executors

import org.apache.kafka.clients.producer.{Callback, KafkaProducer, ProducerRecord, RecordMetadata}

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
object PromisesCreate extends App {
  import scala.concurrent.ExecutionContext.Implicits.global
  val p = Promise[String]
  val q = Promise[String]
  p.future foreach { case x => println(s"p succeeded with '$x'") }
  Thread.sleep(1000)
  p success "assigned"
  q failure new Exception("not kept")
  q.future.failed foreach { case t => println(s"q failed with $t") }
  Thread.sleep(1000)
}

object PromisesCustomAsync extends App {
  import scala.concurrent.ExecutionContext.Implicits.global
  import scala.util.control.NonFatal
  def myFuture[T](b: =>T): Future[T] = {
    val p = Promise[T]
    global.execute(new Runnable {
      def run() = try {
        p.success(b)
      } catch {
        case NonFatal(e) => p.failure(e)
      }
    })
    p.future
  }
  val f = myFuture { "naa" + "na" * 8 + " Katamari Damacy!" }
  f foreach {
    case text => println(text)
  }
}

object FileAlterationMonitor extends App {

  import scala.concurrent.ExecutionContext.Implicits.global
  import org.apache.commons.io.monitor._

  def fileCreated(directory: String): Future[String] = {
    val p = Promise[String]
    val fileMonitor = new FileAlterationMonitor(1000)
    val observer = new FileAlterationObserver(directory)
    val listener = new FileAlterationListenerAdaptor {
      override def onFileCreate(file: File): Unit =
        try p.trySuccess(file.getName) finally fileMonitor.stop()
    }
    observer.addListener(listener)
    fileMonitor.addObserver(observer)
    fileMonitor.start()
    p.future
  }
  fileCreated(".") foreach {
    case filename => println(s"Detected new file '$filename'")
  }
}

object KafkaPromiseDemo {
  val producer: KafkaProducer[String,RecordMetadata] = new KafkaProducer[String,RecordMetadata](new Properties())

  /**
    * Creates a Promise of the expected result type, RecordMetadata
    * @param record
    * @return
    */
  def sendToKafka(record: ProducerRecord[String,RecordMetadata]): Future[RecordMetadata] = {
    val promise: Promise[RecordMetadata] = Promise[RecordMetadata]()
    //Gets a reference to the Future[RecordMetadata] that we can pass on
    val future: Future[RecordMetadata] = promise.future
    //This is the Kafka callback that’s used to indicate that sending has completed. It’s
    // called once after sending the record has completed on another thread.
    val callback = new Callback() {
      def onCompletion(metadata: RecordMetadata, e: Exception): Unit = {
        if (e != null) promise.failure(e) //Writes a failure to the promise if there’s an error
        else promise.success(metadata) //Writes a success to the promise otherwise
      }
    }
    producer.send(record, callback)
    future   //Returns the future to the user of the sendToKafka method
  }
}