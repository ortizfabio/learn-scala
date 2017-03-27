package com.bytetrend.sandbox.scala.example

import java.io.File

object FileMatcherVerbose extends App {
  private def filesHere: Array[File] = (new java.io.File(".")).listFiles

  private def filesMatching(query:String,matcher: (String,String) => Boolean) =
    for {file <- filesHere; if (matcher(file.getName,query))
    } yield file

  def filesEnding(query: String): Array[File] = filesMatching(query, (fileName: String, query: String) => fileName.endsWith(query))
  def filesContaining(query: String): Array[File] = filesMatching(query,(fileName: String, query: String) => fileName.contains(query))
  def filesRegex(query: String): Array[File] = filesMatching(query,(fileName: String, query: String) => fileName.matches(query))
  println(filesEnding(".scala").mkString(","))
}

object FileMatcherNotClosure extends App {
  private def filesHere: Array[File] = (new java.io.File(".")).listFiles

  def filesMatching(query: String, matcher: (String, String) => Boolean) =
    for (file <- filesHere; if matcher(file.getName, query))
      yield file

  def filesEnding(query: String) = filesMatching(query, _.endsWith(_))
  def filesContaining(query: String) = filesMatching(query, _.contains(_))
  def filesRegex(query: String) = filesMatching(query, _.matches(_))
  println(filesEnding(".scala").mkString(","))
}

object FileMatcherClosure extends App {
  private def filesHere = (new java.io.File(".")).listFiles

  private def filesMatching(matcher: String => Boolean) =
    for (file <- filesHere; if matcher(file.getName))
      yield file

  def filesEnding(query: String) = filesMatching(_.endsWith(query))
  def filesContaining(query: String) = filesMatching(_.contains(query))
  def filesRegex(query: String) = filesMatching(_.matches(query))

  //Assigning method filesContaining to a val and calling it
  val f = filesContaining _
  println(f(".classpath").mkString(","))
}

