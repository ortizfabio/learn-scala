package com.bytetrend.sandbox.scala.algo


object HexDecimalSolution extends App {
  /*
    7E0 => 2016
    7 = 7 * 16^2
    E = 14 * 16^1
    0 = 0 * 16^0
    ---------------
        SUM = 2016
    // 7E0 = 2016
  */

  def toDecimal(hex: String): Int = {
    def toDecimialDigit(c: Char): Int = {
      c match {
        case '0' => 0
        case '1' => 1
        case '2' => 2
        case '3' => 3
        case '4' => 4
        case '5' => 5
        case '6' => 6
        case '7' => 7
        case '8' => 8
        case '9' => 9
        case 'A' => 10
        case 'B' => 11
        case 'C' => 12
        case 'D' => 13
        case 'E' => 14
        case 'F' => 15
      }
    }
    var count = -1;
    val l = hex.reverse.map{x => count += 1; toDecimialDigit(x) * math.pow(16,count)}
    l.sum.toInt
  }
  println(toDecimal("7E0"))
}

// To execute Scala, please define an object named Solution that extends App

object HexStringToDecimal extends App {

  /*
  7E0 => 2016

  7 = 7 * 16^2
  E = 14 * 16^1
  0 = 0 * 16^0
  ---------------
      SUM = 2016
  */

  // 7E0 = 2016

  def toDecimal(hex: String): Int = {
    hex.zipWithIndex.map{ case (x,i) =>{
      (if(x >= '0' && x <= '9')( x - '0')else(10 + x - 'A' )) * math.pow(16,(hex.length - 1 -i)).toInt;
    }}.sum
  }

  println(toDecimal("7E0"))
}

