package com.bytetrend.sandbox.scala.hackerrank

import java.io.PrintWriter

/**
  * https://pastebin.com/AVPGcURN
  * https://www.hackerrank.com/challenges/bear-and-steady-gene/problem
  * Assume the gene combination is as below:
  * GTGGCCCA
  * *
  * We have a solution of a substring that has a head index at h and tail index t.
  * From above combination, we know that there is a maximum H for head and minimum T for tail at
  * GTGCCGCA
  * ^ ^
  * T H
  * Because if the substring starts after H, there will not be any valid solution (There will be 3 Gs before the head)
  * And if the substring ends before T, there will not be any valid solution (There will be 3Cs after the tail)
  * So, we know that h <= H and t >= T.
  * *
  * My solution is first to find the T, and set it as the current substring tail solution, t.
  * Then we loop from 0 to H for h. So, h starts from 0 and t starts from T.
  * *
  * for every loop:
  * while the solution is not valid (not valid means the string outside the substring is not valid)
  * increment t
  * if t is larger or equal to the string size
  * break out of the loop
  * solution is just max(0, t - h) (if the t <= h, means that the string is already valid)
  * if current solution length is larger than new solution
  * update current solution
  * then we increment h
  * *
  * reason for the map in the solution is to keep the validity checking O(1). But we need to update it whenever h or t is incremented.
  * In the solution h is the minIndex and t is the maxIndex
  */
object SteadyGene3 {

  // Complete the steadyGene function below.
  def steadyGene(gene: String): Int = {
    import scala.math.max
    import scala.collection.mutable.{Map => MMap}
    import scala.math.min
    println("|" + gene + "|")
    val n = gene.length

    def isSteady(mapCount: MMap[Char, Int]): Boolean = {
      for (x <- mapCount)
        if (x._2 > n / 4)
          return false
      true
    }

    val map = gene.foldLeft((MMap('C' -> 0, 'G' -> 0, 'A' -> 0, 'T' -> 0)))({
      case (map, c: Char) => map(c) += 1
        map
    })
    var (left, right, result) = (0, 0, Integer.MAX_VALUE)
    while (right < n - 1) {
      val rc = gene(right)
      map(rc) = map(rc) - 1
      right += 1
      while (isSteady(map)) {
        if (result > right - left) {
          val a = (Array.fill[String](n)(" ").mkString("").toCharArray).zipWithIndex.map(c => c._2.toString).toArray[String]
          a(left) = "H"
          a(right - 1) = "T"
          println("|" + a.mkString("") + "|")
          println(gene.substring(max(0, left), max(0, right)))
        }
        result = min(result, right - left)
        val lc = gene(left)
        left += 1
        map(lc) = map(lc) + 1
      }
    }
    result
  }


  def main(args: Array[String]) {
    val stdin = scala.io.StdIn
    val printWriter = new PrintWriter(System.out)

    val gene7 = "TGCATGCA"
    val result7 = steadyGene(gene7)
    printWriter.println(result7)
    printWriter.flush()

    val gene6 = "TTTTTTTT"
    val result6 = steadyGene(gene6)
    printWriter.println(result6)
    printWriter.flush()

    val gene5 = "GTGGCCCA"
    val result5 = steadyGene(gene5)
    printWriter.println(result5)
    printWriter.flush()
    // A=-4
    // A=0 c=2 T=1 G=1

    //i=0 j=6 5 == 5
    val gene = "GAAATAAA"
    val result = steadyGene(gene)
    assert(result == 5)
    printWriter.println(result)
    printWriter.flush()

    //i=24 j=30 5 == 5|     |
    val gene2 = "TGATGCCGTCCCCTCAACTTGAGTGCTCCTAATGCGTTGC"

    //i=112 j=1507 1393 == 1393
    //   val gene2 = "ACAAAAATAAACAAAAACAAAAAAAAAATAAATACAATAAAAAAAAAAAATGAAATACAACAACAAATAAAATAAAAACGACTAAAAAATAAAAAAAAAAAAAAAAAGAGTACTAAAAAAAAAAAAAAAAAATAAAAAAAAAAAAAACACAATCAAAATAAACAAAAAAAAAAAAACCAAAATAATCAACAAAAAAAAAAAAAACAAAAACAACAACAAACAAAAAAAAACACAAACAAAAAAAAAAAAAAAACAAAACAAACAAAAAAAAAAAAACAAAAAAACAAAAAAAAAAAAAAAAACAAAAAAAAAAATAAAAAAAAAAAAAAAAAAAAAACAAACAAAAAAAAAAAATACAAAAAGCTATAAAAAAAAAAAAATTAAAAAACAAAAAAAAATAAAAAAAAAAAAAAAAAAAAAAAATAAAAAAAAAAAAAAAAAAAAAATAAAAAAAAAAAAAAAAAAGAAAAACAAAAAAAAAAAAAAAAACAACCAAAAAACAAAAAAAAACTAAAAAAAAAAAAAAAAAAAAAAAAAAATAACAAAAAACACAAAAAAAAAAAAGAAAGAAAAAAAACACAAAAAAAAACAAACAAAAAAAAAAAAAAAAAAAGAAAACAAAAAAACAAAAAAAACAAAAAAAAAACAAAAATTGGACAAAAAAAAACAAAAAAAAAAAACAAAAAAAGTAAAACAAATAAAAAAACAAAAAAAACAAAAAAAAAAAAAAAAAACAAAAAAGAAACAAAAAACAAAAAAAAATAACAAAACCAAAAAACAAATAAAAAACAAAAAAAATAACACAAAAAAAAAAAGAAACAAAAAAAAAAAAAAAAAAAAAAATTATAAAAAAAAAAAAAAAACAAAAAAAAAAAAAACAAAAAAAAAAGGAAAAAAAAAAAAAAAAAAAAAAAAAAATAACTAAACAAAAAAAAACAAACAAAAAATCAAAAAAAAAAAAGAAAAAAGAATAAGCAACAAAAACACAAAAAAAAAAAAAAAAAAAAAAAACATAAACAATAATAAAAAAAAAACAAAAAAAACAAAAGAACAACAAAAAACAAAACTAAACAAATAAAAAAAAAAAAACAAAAACTACAAAAAAAAAAAGAAAAAAAAAGAAAAAAAAACAAATAAAAGAAAAAAAAAAAAAAAAAAAACACAAAAAAAAAAATAAAAAAAAAAAAAAAAACAAAATAAACAAAAACAAAGAAAAAAACAAACAAAAAAAAAAAACAAAAAACTAAAAACAAAAAAAAAACAAAACACAAAAAAAAAAAAAAATAAAAAAAAAACAAAAAAACAAAAAGGAAAAAAAAAAAAGAACAAAAAAAAAAACAACAGAAAAAAGAAAAGAAAAAAAAAAAAAGACCACAAAATAAAAAAAAACAACAAACAAAAAAAAACAAAACAAAAAAACGAACAAAAAAAACAAAAACAAAAAAAAAAAAAAAAAAAAAAAGGCAAAAACAAAAAAAACAAAACAAAACAAAAAAACAAAAAAAAATTAAGATAAAGAACAAAAAAAGAAGAGAAAAAATTAACAAAAAAAAAAAAATAAAAAATACAAAAAGAAATAAAAAATACAACACACAACAAAAACGAAAAAAAAAAAAAAAACACAAAATAGAAAAAAAAAAAAAACAAAAAAAAAAAAAAGAAAAAAACAAAAAAAAAAAAATAAAAAAAAACGACACAGAAACAAAAAATAACAAAAAAAAAAAAAATAAAAAAAAAACAAAAAAAAAACAAAAAATAAAAAAAAAAACAAACAAAAAAAAAAAAAAAATAAAAAAAAAAAAAGCAAAACATAAACAAGAAAAAAAAAAAAAGTACAAATAACAAAACAAAAAAGACACTAAAAAAAAAAAAAAAAAACAAAAAAAAAAAAAAAAAAAAAAAAAGAAAAAAAACCACAAAACAAAAAAATAAAGCAAAAAAAAAAAAAAAAAAAAAAAAAAAATAAATGAAAAAAAAAAGAAAACCAAAAAAATAAAAGA"
    val result2 = steadyGene(gene2)
    printWriter.println(result2)
    printWriter.flush()
    //assert(result2 == 1393)

    val gene3 = "AAAACCCC"
    val result3 = steadyGene(gene3)
    printWriter.println(result3)
    printWriter.flush()

    val gene4 = "AAAA"
    val result4 = steadyGene(gene4)
    printWriter.println(result4)
    printWriter.flush()

    printWriter.close()
  }

}
