package com.bytetrend.sandbox.scala.encryption

trait DataGenerator {
  val random = new scala.util.Random
  /**
    * Generate a random string of given length from supplied characters
    * @param alphabet
    * @param n
    * @return
    */
  def stringGen(alphabet: String)(n: Int): String =
    Stream.continually(random.nextInt(alphabet.size)).map(alphabet).take(n).mkString

  /**
    * Generate a random string of length n with alpha and numeric characters
    * @param n
    * @return
    */
  def alphanumericStringGen(n: Int) = stringGen("abcdefghijklmnopqrstuvwxyz0123456789")(n)

  /**
    * makes string complaint with the given max lenth.
    * @param s generated random string.
    * @param maxLength maximum length allowed.
    * @return string upto with length upto maxLength.
    */
  def maxLength(s: String, maxLength: Int): String = {
    (s.length - maxLength) match {
      case 0 => s
      case n if n < 0 => s.concat(alphanumericStringGen(-1 * n))
      case n if n > 0 => s.substring(0, maxLength)
    }
  }


}
