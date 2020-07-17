package com.bytetrend.sandbox.scala.encryption

object CypherDemo extends DataGenerator {
  def main(args: Array[String]): Unit = {
    val count = 1000000000
    val max_str_len = 4096
    val cypherUtil = new CypherUtil("kjisoJDHH187@#%*")
    val decrypt = (s: String) => cypherUtil.decrypt(cypherUtil.encrypt(s))

    for (_ <- 0 until count) {
      val a = alphanumericStringGen(max_str_len)
      val ta = task(decrypt(a))
      val b = alphanumericStringGen(max_str_len)
      val tb = task(decrypt(b))
      val c = alphanumericStringGen(max_str_len)
      val tc = task(decrypt(c))
      val d = alphanumericStringGen(max_str_len)
      val td = task(decrypt(d))
      val (resulta, resultb, resultc, resultd) = parallel(ta, tb, tc, td)
      println(s"a string encryption/decryption is: ${a.equals(resulta.get())}")
      println(s"b string encryption/decryption is: ${b.equals(resultb.get())}")
      println(s"c string encryption/decryption is: ${c.equals(resultc.get())}")
      println(s"d string encryption/decryption is: ${d.equals(resultd.get())}")

    }
  }

}
