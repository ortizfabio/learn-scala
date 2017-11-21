package com.bytetrend.sandbox.scala.challenge

/**
  * <tapad>
  * coderpad io
  * Created by db2admin on 17/10/20.
  */

object MineField extends App {
  var mines = 0

  case class BoardSquare(x: Int, y: Int, hasMine: Boolean)

  def mine(xCount: Int, yCount: Int, maxMines: Int) = {
    val list = for {
      i <- 0 to xCount
      j <- 0 to yCount
    } yield {
      BoardSquare(i, j, isMine((xCount + 1), (yCount + 1), maxMines))
    }
    list
  }

  def isMine(x: Int, y: Int, max: Int): Boolean = {
    if (mines > max)
      false
    else
      ((new java.util.Random()).nextInt(x + 1) % 2) match {
        case 0 => {
          mines = mines + 1
          true
        }
        case _ => false
      }
  }

  println(mine(4, 3, 2))
}