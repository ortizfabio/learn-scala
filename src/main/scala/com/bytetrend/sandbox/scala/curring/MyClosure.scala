package com.bytetrend.sandbox.scala.curring

object MyClosure {

  def m1(multiplier: Int => Int) = {
    (1 to 10) filter (_ % 2 == 0) map multiplier reduce (_ * _)
  }

  def m2: Int => Int = {
    val factor = 2
    val multiplier = (i: Int) => i * factor
    multiplier
  }

  /**
    * There are two variables in multiplier: i and factor. One of them, i, is a formal parameter
    * to the function. Hence, it is bound to a new value each time multiplier is called.
    * Functional Programming in Scala | 173
    * However, factor is not a formal parameter, but a free variable, a reference to a variable
    * in the enclosing scope. Hence, the compiler creates a closure that encompasses (or “closes
    * over”) multiplier and the external context of the unbound variables that multipli-
    * er references, thereby binding those variables as well.
    *
    * @param args
    */
  def main(args: Array[String]): Unit = {
    var factor = 3
    val multiplier = (i: Int) => i * factor
    println(s"with factor =$factor is =" + ((1 to 10) filter (_ % 2 == 0) map multiplier reduce (_ * _)))
    factor = 4
    println(s"with factor =$factor is =" + ((1 to 10) filter (_ % 2 == 0) map multiplier reduce (_ * _)))

    println("closure call factor is 2 " + m1(m2))
  }
}
