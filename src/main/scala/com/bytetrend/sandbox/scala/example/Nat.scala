package com.bytetrend.sandbox.scala.example

/**
  * //Peano numbers
  * Created by db2admin on 7/30/2016.
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
}

class Succ(n: Nat) extends Nat {
  override def isZero: Boolean = false

  override def +(that: Nat): Nat = new Succ(n + that.predecessor)

  override def -(that: Nat): Nat = this - that

  override def predecessor: Nat = this.predecessor
}