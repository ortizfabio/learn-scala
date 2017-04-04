package com.bytetrend.sandbox.scala.algo

import scala.annotation.tailrec
import scala.io.StdIn.readInt


object PrintIntAsBinary {
  @tailrec
  def toBinary(i : Int, seq: List[Int] = List.empty[Int]): String ={
    if(i == 0) {
      if (seq.isEmpty)
        "0" //seq is a val and can not be appended otherwise use (0::seq).mkString("")
      else
        seq.mkString(" ")
    } else
      toBinary(i>>>1,(i%2) :: seq)
  }

  def main(args : Array[String]): Unit ={
    println("Enter an integer ")
    val x = readInt()
    println(x+" in binary notation: "+toBinary(x))
  }

}
