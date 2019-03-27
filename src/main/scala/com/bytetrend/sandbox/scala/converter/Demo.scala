package com.bytetrend.sandbox.scala.converter

/**
  * Created by jose.ortiz on 8/5/16.
  */
object Demo extends App {

  import play.api.libs.json._


  val path = "./target/scala-2.11/classes/Voyage1606GuestInfo.csv"

  val graph = CSVIngest(path)


  val jsonText :String = graph.toJsonString()

  val result: JsValue = Json.parse(jsonText)
  val agentIndicator = (result \ "Agent Indicator").get
  println(agentIndicator)
  println(Json.prettyPrint(result))
//  import com.carnival.messages.Message

  val jsonSeq = graph.toJsonObjectSeq()
//  println(Json.prettyPrint(JsObject(jsonSeq)))

}