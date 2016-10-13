package com.bytetrend.sandbox.scala.example

class LoggingThread {

  private var linesToLog:List[String] =  Nil;
  @volatile private  var terminateRequested: Boolean = false;
  
}