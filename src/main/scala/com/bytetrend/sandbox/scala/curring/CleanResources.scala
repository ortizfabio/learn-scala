package com.bytetrend.sandbox.scala.curring

import java.io.{BufferedWriter, FileOutputStream, OutputStreamWriter}
import java.nio.file.{FileSystem, Path}

import org.scalatest.prop.Configuration

import scala.util.{Failure, Success, Try}

/**
  * https://www.phdata.io/try-with-resources-in-scala/
  */
object CleanResources extends App{

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
  val tempdir = for{
    (k,v) <- System.getProperties
    if("java.io.tmpdir".equals(k))
  }yield v

  tempdir.toList match  {
    case head :: tail =>
    cleanly(new FileOutputStream(s"$head/demo"))(_.close()) { os =>
      val bufferedWriter = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"))
      bufferedWriter.write("some data bytes")
      bufferedWriter.newLine()
      bufferedWriter.flush()
    }
    case Nil =>
  }
}

