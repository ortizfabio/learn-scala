package com.bytetrend.sandbox.scala.algo

import scala.collection.mutable.ListBuffer

/**
  * https://www.careercup.com/question?id=5699214707785728
  * Implement binary addition of two strings.
  * For example "101101" and "111101" equal "1101010"
  * You cannot use any type conversion, operate only with strings.
  */
object BinaryAdditionOfTwoStrings extends App {

  def sum(a: String, b: String): String = {
    var c = '0'
    var ai = a.length - 1
    var aj = b.length - 1
    val l: ListBuffer[Char] = new ListBuffer[Char]
    while (ai > -1 || aj > -1) {
      val x = if (ai > -1) a(ai) else '0'
      val y = if (aj > -1) b(aj) else '0'
      if (x == '0' && y == '0' && c == '0')
        l += '0'
      else if (x == '0' && y == '0' && c == '1') {
        c = '0'
        l += '1'
      } else if (x == '0' && y == '1' && c == '0')
        l += '1'
      else if (x == '0' && y == '1' && c == '1') {
        c = '1'
        l += '0'
      } else if (x == '1' && y == '0' && c == '0')
        l += '1'
      else if (x == '1' && y == '0' && c == '1') {
        c = '1'
        l += '0'
      } else if (x == '1' && y == '1' && c == '0') {
        c = '1'
        l += '0'
      } else { //(x == '1' && y == '1' && c == '1')
        c = '1'
        l += '1'
      }
      ai -= 1
      aj -= 1
    }
    if (c == '1')
      l += c

    new String(l.toArray).reverse
  }

  println(f"${sum("101101", "111101")} should be equal to 1101010")
}

object BinaryAdditionOfTwoString2 {
  //Dictionary to previous reminder follow by two digits from inputs
  //Result is new reminder plus current digit value.
  // (prev_reminder, ( 1st dgt, 2nd dgt)) -> (new_reminder,result dgt)
  val d: Map[(Char, (Char, Char)), (Char, Char)] = Map(
    ('0', ('0', '0')) -> ('0', '0'),
    ('0', ('0', '1')) -> ('0', '1'),
    ('0', ('1', '0')) -> ('0', '1'),
    ('0', ('1', '1')) -> ('1', '0'),
    ('1', ('0', '0')) -> ('0', '1'),
    ('1', ('0', '1')) -> ('1', '0'),
    ('1', ('1', '0')) -> ('1', '0'),
    ('1', ('1', '1')) -> ('1', '1')
  )

  def sum(a: Array[Char], b: Array[Char]): String = {
    var c = '0'
    val l = a.reverse.zipAll(b.reverse, '0', '0').foldLeft(List[Char]()){case (acc:List[Char], p:(Char,Char)) => {
      val (i, j) = d((c, p))
      c = i
      j :: acc
    }}

    new String((c::l).toArray)
  }

  def main(args: Array[String]) = {
    println(f"${sum("101101".toArray, "111101".toArray)} should be equal to 1101010")
  }
}