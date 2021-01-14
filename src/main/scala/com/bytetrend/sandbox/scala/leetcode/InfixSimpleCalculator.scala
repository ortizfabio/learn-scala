package com.bytetrend.sandbox.scala.leetcode

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

object InfixSimpleCalculator {

  lazy val operatorsPermitted = Set('+', '-', '*', '/', '^')
  lazy val numbers = ('0' until '9').toSet
  lazy val brackets = Set('(', ')')
  lazy val validCharacter = brackets ++ operatorsPermitted ++ numbers ++ Set(' ')

  def whileLoop(cond: => Boolean)(block: => Unit): Unit = {
    if (cond) {
      block
      whileLoop(cond)(block)
    } else {
      println(s"Invalid $cond")
      sys.exit(1)
    }

    def eval(exp: Array[Char]): Int = {
      val operators = mutable.Stack[Int]()
      val numeric = mutable.Stack[Int]()
      val currentNum = ArrayBuffer[Char]()
      var i = 0
      whileLoop(i < exp.length && validCharacter.contains(exp(i))) {
        val c = exp(i)
        if (numbers.contains(c))
          currentNum += c
        else if (operatorsPermitted.contains(exp(i))) {
          operators.push(c)
          if(currentNum.length > 0) {
            numeric.push(Integer.parseInt(String.valueOf(currentNum)))
            currentNum.clear()
          }
        }else if(brackets.contains(c)){

        }
        i += 1
      }
0
    }

  }

}
