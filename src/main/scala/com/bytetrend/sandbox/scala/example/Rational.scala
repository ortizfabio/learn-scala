package com.bytetrend.sandbox.scala.example

/**
 * Created by db2admin on 4/15/2016.
 */
class Rational(n: Int, d: Int) {
  require(d != 0)
  private val g = gcd(n.abs, d.abs)
  val numer = n / g
  val denom = d / g

  private def gcd(a: Int, b: Int): Int =
    if (b == 0) a else gcd(b, a % b)

  def this(n: Int) = this(n, 1)

  def +(that: Rational): Rational =
    new Rational( numer * that.denom + that.numer * denom,   denom * that.denom
    )

  def +(i: Int): Rational =
    new Rational(numer + i * denom, denom)

  def -(that: Rational): Rational =
    new Rational(numer * that.denom - that.numer * denom, denom * that.denom)

  def -(i: Int): Rational =
    new Rational(numer - i * denom, denom)

  def *(that: Rational): Rational =
    new Rational(numer * that.numer, denom * that.denom)

  def *(i: Int): Rational =
    new Rational(numer * i, denom)

  def /(that: Rational): Rational =
    new Rational(numer * that.denom, denom * that.numer)

  def /(i: Int): Rational =
    new Rational(numer, denom * i)

  override def toString = numer + "/" + denom
}

object Rational {
  def main(args: Array[String]): Unit = {
    def r1 = new Rational(10,2)
    println(r1)
    def r2 = new Rational(3,2)
    println(r2)
    def r1plusr2 = r1 + r2
    println(r1plusr2)
    def r2minusr1 = r2 - r1
    println(r2minusr1)
    println("r1 "+r1+" r2 "+r2+" "+r1plusr2+r1plusr2+" r2minusr1"+r2minusr1)

    println(2 * r2)
  }
}