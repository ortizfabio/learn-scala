package com.bytetrend.sandbox.scala.example

object Bloomberg extends App {
  // This is the text editor interface.
  // Anything you type or change here will be seen by the other person in real time.
  // To execute Scala, Do not remove the object named Solution that extends App.

  // Print the counts of consecutive letter sequences in a string. (Ex: "aaaabbbbbcdd" -> "4 a, 5 b, 1 c, 2 d" or "aaaabbbbbcddaaabb" -> "4 a, 5 b, 1 c, 2 d, 3 a, 2 b")

  import collection.mutable.ArrayBuffer

  def process(input: String): Unit = {
    val list: ArrayBuffer[(Char, Int)] = ArrayBuffer[(Char, Int)]()
    var count = 1
    var char: Char = input.head
    for (c <- input.tail) {
      if (c == char) {
        count += 1
      } else {
        list += ((char, count))
        count = 1
      }
      char = c
    }
    list += ((char, count))
    println(list.map(x => s"${x._2} ${x._1}").mkString(", "))
  }

  process("aaaabbbbbcdd")
}

object Bloomberg2 extends App {

  // This is the text editor interface.
  // Anything you type or change here will be seen by the other person in real time.
  // To execute Scala, Do not remove the object named Solution that extends App.

  // Print the counts of consecutive letter sequences in a string. (Ex: "aaaabbbbbcdd" -> "4 a, 5 b, 1 c, 2 d" or "aaaabbbbbcddaaabb" -> "4 a, 5 b, 1 c, 2 d, 3 a, 2 b")

  import collection.mutable.ArrayBuffer

  def process(input: String): Unit = {

    val dup = input.groupBy(identity).map(x => (x._1,x._2.length))

    println(dup.map(x => s"${x._2} ${x._1}").mkString(", "))
  }

  process("aaaabbbbbcddaaabb")
}
