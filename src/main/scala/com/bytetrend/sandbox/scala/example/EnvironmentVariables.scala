package com.bytetrend.sandbox.scala.example


object EnvironmentVariables extends App {
  import scala.collection.JavaConversions._
  import scala.sys.props
//  val environmentVars = System.getenv()
 // for ((k,v) <- environmentVars) println(s"key: $k, value: $v")

//  val properties = System.getProperties()
 // for ((k,v) <- properties) println(s"key: $k, value: $v")

  for((k,v) <- sys.env) println(s"key: $k, value: $v")
  for ((k,v) <- props) println(s"key: $k, value: $v")

}