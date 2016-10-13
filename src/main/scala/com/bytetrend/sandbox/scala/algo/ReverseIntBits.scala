package com.bytetrend.sandbox.scala.algo

import scala.io.StdIn.readInt
/**
  * Created by db2admin on 6/6/2016.
  */
object ReverseIntBits {

  def toBinary(i : Int, seq: List[Int] = List.empty[Int]): String ={
    if(i == 0) {
      if (seq.isEmpty)
        "0" //seq is a val and can not be appended otherwise use (0::seq).mkString("")
      else
        seq.mkString(" ")
    } else toBinary(i>>>1,(i%2) :: seq)
  }

  def main(args : Array[String]): Unit ={
    println("Enter an integer ")
    val x = readInt()
    println("In binary notation: "+toBinary(x))
  }
}
