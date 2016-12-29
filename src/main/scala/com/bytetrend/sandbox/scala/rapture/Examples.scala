package com.bytetrend.sandbox.scala.rapture
/*
import rapture.json.Json

import scala.reflect.api.Symbols
class Examples {
  import scala.reflect.runtime.universe.MethodSymbol
  def toJson: Json = {
    val rm = scala.reflect.runtime.currentMirror
    val accessors = rm.classSymbol(this.getClass).toType.members.collect {
      case m: MethodSymbol if m.isGetter && m.isPublic => m
    }
    val instanceMirror = rm.reflect(this)

    accessors.foldLeft(Json.empty)((out:Json, acc:MethodSymbol) => {
      val value:Any = instanceMirror.reflectMethod(acc).apply()
      val fieldName:String = acc.name.toString
      out ++  """ {"$fieldName":$value}"""
   } )
  }
}

import rapture.codec.encodings
import rapture.io._
import rapture.core._
import rapture.json._

object RaptureTest {

  def main(args: Array[String]) {
    implicit val enc = encodings.`UTF-8`

    // implicit val eh = captureExceptions ???
    val raw = """{ "name": "Frank", "age": 23}"""

    val json:Json = Json.parse(raw)
    json match {
      case Right(b) => b.name.as[String]
      case Left(error) => error.getMessage
    }
  }
}
  */
