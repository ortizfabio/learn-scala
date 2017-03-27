package com.bytetrend.sandbox.scala.exceptions

import java.net.URL

import scala.util.Try


object TryExamples extends App{

  def parseURL(url:String) :Try[URL] = Try(new URL(url))

  import java.io.InputStream
  def inputStreamForURLwitMap(url: String): Try[Try[Try[InputStream]]] = parseURL(url).map { u =>
    Try(u.openConnection()).map(conn => Try(conn.getInputStream))
  }
  def inputStreamForURL(url: String): Try[InputStream] = parseURL(url).flatMap { u =>
    Try(u.openConnection()).flatMap(conn => Try(conn.getInputStream))
  }


  val result:Try[Try[Try[InputStream]]] = inputStreamForURLwitMap("not a urla")
  println(result.flatten)

  val result2 =inputStreamForURL("not a url")

  println(result2)
}
