package com.bytetrend.sandbox.scala

import scala.util.parsing.json.JSONArray
import scala.collection.mutable.ArrayBuffer
import scala.io.Source
import scala.util.parsing.json.JSONFormat.ValueFormatter
import scala.util.parsing.json.{JSONArray, JSONObject}
import scala.util.parsing.json.JSONFormat.defaultFormatter
import scala.language.reflectiveCalls

package object converter {

  private[converter] def isNumeric(input: String): Boolean = input.forall(_.isDigit)

  /**
    * Maps strings to JSON values.
    * When s is null it returns string "null".
    * if s is an integer it will return that string
    * anything else it returns a quoted string like "s"
    *
    * @param s a string value to be processed.
    * @return a JSON string representation
    */
  private[converter] def jsonString(s: String): String = s match {
    case s if (s == null || s.isEmpty) => "null"
    case s if (isNumeric(s)) => s
    case _ => s""""${s}""""
  }

  private[converter] def jsonValue(s: String): Any = s match {
    case s if (s == null || s.isEmpty) => null
    case s if (isNumeric(s)) => s
    case _ => s""""${s}""""
  }

  /**
    * This function takes to sequences one with name of fields (headers) and another with their
    * values and binding together in a JSON object. This function does not validates if the
    * length of the sequences are the same in that case it process to the smallest length.
    * example:
    * {  "CCN" : "101318901C , "Birthday" : "1962-12-18 , "Age" : 53 , "Gender" : "M , "Customer Status" : "Active , "Anniversary Date" : null   }
    *
    * @param header a sequence of strings with field names
    * @param body   a sequence of strings with field values.
    * @return a sequence of strings that represent JSON objects
    */
  private[converter] def makeJsonString(header: Seq[String], body: Seq[String]): String = {
    "{ " + (header zip body).map(x => s""" "${x._1}" : ${jsonString(x._2)} """).mkString(",") + " }"
  }

  /**
    * A JSONObject is defined as Map[String,Any])
    *
    * @param header a sequence of strings with field names
    * @param body   a JSONObject as
    * @return
    */
  private[converter] def makeJsonObject(header: Seq[String], body: Seq[String]): JSONObject = {
    JSONObject((header zip body).map(x => (x._1 -> jsonValue(x._2))).toMap)
  }

  private[converter] def using[A <: {def close() : Unit}, B](resource: A)(f: A => B): B =
    try {
      f(resource)
    } finally {
      resource.close()
    }

  /**
    * Takes a file and return two sequences one with the header titles and another
    * with the data in the following rows. It assumes the first row is always the
    * header.
    *
    * @param path a path to a CSV file
    * @return a pair of sequences one with the title and
    */
  private[converter] def fromCsv(path: String): (Seq[String], Seq[Seq[String]]) = {

    // each row is an array of strings (the columns in the csv file)
    val rows = ArrayBuffer[Seq[String]]()
    val header = ArrayBuffer[String]()
    // (1) read the csv data
    using(Source.fromFile(path))({ source =>

      for (line <- source.getLines) {
        if (header.size == 0)
          line.split(",").foreach(x => header += s""""${x}"""")
        else
          rows += line.split(",").map(_.trim)
      }
    })
    (header.toSeq, rows.toSeq)
  }

  /**
    * The default formatter used by the library. You can
    * provide your own with the toString calls on
    * JSONObject and JSONArray instances.
    */
  val jsonFormatter: ValueFormatter = (x: Any) => x match {
    case null => "null"
    case jo: JSONObject => jo.toString(defaultFormatter)
    case ja: JSONArray => ja.toString(defaultFormatter)
    case other => if (other == null) "null" else other.toString
  }

}



