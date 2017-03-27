package com.bytetrend.sandbox.scala.example

/**
  * //Peano numbers
  */
abstract class Nat {
  def isZero: Boolean

  def predecessor: Nat

  def successor = new Succ(this)

  def +(that: Nat): Nat

  def -(that: Nat): Nat
}

object Zero extends Nat {

  override def isZero: Boolean = true

  override def +(that: Nat): Nat = that

  override def -(that: Nat): Nat = if (that.isZero) this else throw new Error("Negative number")

  override def predecessor: Nat = throw new Error("0.predecessor")

  override def toString = "0"
}

class Succ(n: Nat) extends Nat {
  override def isZero: Boolean = false

  override def +(that: Nat): Nat =
    if (that.isZero)
      this
    else {
      val nat = that.successor
      n + nat
    }

  override def -(that: Nat): Nat =
    if (that.isZero)
      this
    else {
      val nat = that.predecessor
      n - nat
    }

  override def predecessor: Nat = n

  override def toString = (n.toString.toInt + 1).toString
}

object test extends App {
  val One = Zero.successor
  println(Zero)
  println(Zero.successor)
  val Two = Zero.successor + Zero.successor
  println(Two)
  println(Two + One)
  println(Two - One)
}