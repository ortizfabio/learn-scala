package com.bytetrend.sandbox.scala.geeksforgeeks

/**
  * https://www.hackerrank.com/challenges/balanced-brackets
  *
  *
  */
object BalancedParentheses {

  def main(args: Array[String]): Unit = {
    val openParentheses = Array('{', '(', '[')
    val closeParentheses = Array('}', ')', ']')
    val in = new java.util.Scanner(System.in)
    val n = in.nextLine.toInt
    for (i <- 1 to n)
      if (isBalanced(in.nextLine))
        println("YES")
      else println("NO")

    def isBalanced(text: String): Boolean = {
      import collection.mutable.Stack
      val stack = Stack[Char]()
      text.toCharArray.foreach(x => {
        val o = openParentheses.indexOf(x)
        if (o > -1) {
          stack.push(closeParentheses(o))
        } else if (stack.isEmpty || stack.pop() != x)
          return false
      }
      )
      if(!stack.isEmpty)
        return false
      return true
    }
  }
}
