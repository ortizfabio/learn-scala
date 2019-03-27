package com.bytetrend.sandbox.scala.converter

import scala.util.parsing.json.JSONObject

/**
  * Created by jose.ortiz on 8/4/16.
  */
abstract class Ingest {
  def toJsonStringSeq(): Seq[String]

  def toJsonObjectSeq(): Seq[JSONObject]

  def toJsonString():String
}

class CSVIngest(path: String) extends Ingest {
  val (header, body) = fromCsv(path)
  require(body.length > 0, "CSV no data found")
  require(header.length == body(0).length, "CSV header and data need to have the same length")

  override def toJsonStringSeq(): Seq[String] = {
    body.map(x => makeJsonString(header, x))
  }

  override def toJsonObjectSeq(): Seq[JSONObject] = {
    body.map(x => makeJsonObject(header, x))
  }

  override def toJsonString():String = {
    body.map(x => makeJsonObject(header,x).toString(jsonFormatter)).mkString("")
  }
}

object CSVIngest {
  def apply(path: String): CSVIngest = {
    new CSVIngest(path)
  }
}