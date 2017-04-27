package com.bytetrend.sandbox.scala.curring

import java.io._

import scala.util.{Failure, Success, Try}

/**
  * https://www.phdata.io/try-with-resources-in-scala/
  */
object CleanResources extends App {
  def displayResult(result: Try[Boolean]) = {
    result match {
      case Failure(e) => println("Work failed due to " + e.toString)
      case Success(x) => println("Work succeeded " + x)
    }
  }

  def cleanly[A, B](resource: A)(cleanup: A => Unit)(doWork: A => B): Try[B] = {
    try {
      Success(doWork(resource))
    } catch {
      case e: Exception => Failure(e)
    }
    finally {
      try {
        if (resource != null) {
          cleanup(resource)
        }
      } catch {
        case e: Exception => println(e) // should be logged
      }
    }
  }

  import scala.collection.JavaConversions._

  val tempdir = for {
    (k, v) <- System.getProperties
    if ("java.io.tmpdir".equals(k))
  } yield v

  val success: Try[Boolean] = tempdir.toList match {
    case head :: tail =>
      //In this curried function the last list of input parameters
      //is passed as a body for the parameter (doWork: A => B)
      //in this dowork input parameter A is os with a type of FileInputStream
      cleanly(new FileOutputStream(s"$head/demo"))(_.close()) { os =>
        val bufferedWriter = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"))
        bufferedWriter.write("some data bytes")
        bufferedWriter.newLine
        bufferedWriter.flush
        true
      }
    case Nil => Failure(new Exception("No File name"))
  }
  displayResult(success)

  val failure: Try[Boolean] = {

    val result = tempdir.toList match {
      case head :: tail =>
        //Since the tail here is empty there will be an exception thrown before
        //The try block in cleanly function is executed. Therefore we have a try
        //to create the FileOutputStream and return an option. The map in the option
        //uses a partial function and if the option is None it skips the rest of the computation.
        val opt = Try(new FileOutputStream(s"$tail/demo")).toOption;
        opt.map(out =>
          cleanly(out)(_.close()) { os =>
            val bufferedWriter = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"))
            bufferedWriter.write("some data bytes")
            bufferedWriter.newLine
            bufferedWriter.flush
            true
          }
        ).getOrElse(Success(false))
      case Nil => Failure(new Exception("No File name"))
    }
    result
  }
  displayResult(failure)
}

