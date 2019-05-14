package com.bytetrend.sandbox.scala.hackerrank


import java.io.PrintWriter


object SteadyGene1 {


  // Complete the steadyGene function below.
  def steadyGene(gene: String): Int = {

    import scala.math.{max, min}
    case class LetterDelta(extra: Int, missing: Int)
    import scala.collection.mutable.{Map => MMap}

    /**
      * Pick a letter from the missing letter set.
      * @param charToDelta
      * @return
      */
    def pickLetter(charToDelta: MMap[Char, LetterDelta]): String = {
      charToDelta.foldLeft("")({ (acc, extMiss) => {
        if (acc.equals("") && extMiss._2.missing > 0) {
          charToDelta(extMiss._1) = LetterDelta(extMiss._2.extra, extMiss._2.missing - 1)
          extMiss._1.toString
        } else
          acc
      }
      })
    }

    /**
      * There are more missing letters to be selected.
      * @param charToDelta
      * @return
      */
    def hasMore(charToDelta: MMap[Char, LetterDelta]): Boolean = {
      charToDelta.foldLeft(false)({ (acc, extMiss) => {
        if (!acc) {
          if (extMiss._2.extra > 0)
            true
          else
            acc
        } else
          acc
      }
      })
    }

    /**
      * Creates a map of letters that
      * @param str
      * @return
      */
    def calculateReplacement(str: String): MMap[Char, LetterDelta] = {
      val maxPerLtrCnt = str.length / 4
      val map: MMap[Char, Int] = MMap('C' -> 0, 'G' -> 0, 'A' -> 0, 'T' -> 0)
      for (c <- str) {
        map(c) = map(c) + 1
      }
      for ((ltr, ltrCnt) <- map) yield (ltr -> LetterDelta(max(0, ltrCnt - maxPerLtrCnt), min(maxPerLtrCnt, max(0, maxPerLtrCnt - ltrCnt))))
    }

    val remAdd: MMap[Char, LetterDelta] = calculateReplacement(gene)
    var shortest = gene.toString
    var i = 0
    var j = i
    println(s"A=${remAdd('A')} G=${remAdd('G')}  T=${remAdd('T')}  C=${remAdd('C')}")

    val toBefound = remAdd.foldLeft(0)((x, y) => x + y._2.missing)
    //    println(remAdd.toSeq.mkString(","))
    while (i < gene.length - toBefound && shortest.length > toBefound) {
      j = i
      val tempMap = MMap() ++ remAdd
      var removeStr = ""
      var addStr = ""
      while (j < gene.length && hasMore(tempMap) && removeStr.length < shortest.length) {
        val c = gene(j)
        val delta = tempMap(c)
        if (delta.extra > 0) {
          tempMap(c) = LetterDelta(delta.extra - 1, delta.missing)
          addStr = addStr + pickLetter(tempMap)
        } else if (addStr.length > 0)
          addStr = addStr + c

        if (addStr.length > 0)
          removeStr = removeStr + c
        j += 1
      }
      assert(removeStr.length == addStr.length)
      if (removeStr.length < shortest.length && tempMap('A').extra == 0 && tempMap('G').extra == 0 && tempMap('T').extra == 0 && tempMap('C').extra == 0 ) {
        println(s"i=${i} j=${j} ${removeStr.length} == ${addStr.length}\n${addStr}\n${removeStr}\n${gene.replace(removeStr,addStr)}")
        println(s"A=${tempMap('A')} G=${tempMap('G')}  T=${tempMap('T')}  C=${tempMap('C')}")
        shortest = removeStr
      }
      i += 1

    }
    shortest.length

  }

  def main(args: Array[String]) {
    val stdin = scala.io.StdIn

    val printWriter = new PrintWriter(System.out)
    // A=-4
    // A=0 c=2 T=1 G=1
        val gene = "GAAATAAA"
    println(gene)
       val result = steadyGene(gene)
      assert(result==5)
      printWriter.println(result)

    /**
    val gene2 = "ACAAAAATAAACAAAAACAAAAAAAAAATAAATACAATAAAAAAAAAAAATGAAATACAACAACAAATAAAATAAAAACGACTAAAAAATAAAAAAAAAAAAAAAAAGAGTACTAAAAAAAAAAAAAAAAAATAAAAAAAAAAAAAACACAATCAAAATAAACAAAAAAAAAAAAACCAAAATAATCAACAAAAAAAAAAAAAACAAAAACAACAACAAACAAAAAAAAACACAAACAAAAAAAAAAAAAAAACAAAACAAACAAAAAAAAAAAAACAAAAAAACAAAAAAAAAAAAAAAAACAAAAAAAAAAATAAAAAAAAAAAAAAAAAAAAAACAAACAAAAAAAAAAAATACAAAAAGCTATAAAAAAAAAAAAATTAAAAAACAAAAAAAAATAAAAAAAAAAAAAAAAAAAAAAAATAAAAAAAAAAAAAAAAAAAAAATAAAAAAAAAAAAAAAAAAGAAAAACAAAAAAAAAAAAAAAAACAACCAAAAAACAAAAAAAAACTAAAAAAAAAAAAAAAAAAAAAAAAAAATAACAAAAAACACAAAAAAAAAAAAGAAAGAAAAAAAACACAAAAAAAAACAAACAAAAAAAAAAAAAAAAAAAGAAAACAAAAAAACAAAAAAAACAAAAAAAAAACAAAAATTGGACAAAAAAAAACAAAAAAAAAAAACAAAAAAAGTAAAACAAATAAAAAAACAAAAAAAACAAAAAAAAAAAAAAAAAACAAAAAAGAAACAAAAAACAAAAAAAAATAACAAAACCAAAAAACAAATAAAAAACAAAAAAAATAACACAAAAAAAAAAAGAAACAAAAAAAAAAAAAAAAAAAAAAATTATAAAAAAAAAAAAAAAACAAAAAAAAAAAAAACAAAAAAAAAAGGAAAAAAAAAAAAAAAAAAAAAAAAAAATAACTAAACAAAAAAAAACAAACAAAAAATCAAAAAAAAAAAAGAAAAAAGAATAAGCAACAAAAACACAAAAAAAAAAAAAAAAAAAAAAAACATAAACAATAATAAAAAAAAAACAAAAAAAACAAAAGAACAACAAAAAACAAAACTAAACAAATAAAAAAAAAAAAACAAAAACTACAAAAAAAAAAAGAAAAAAAAAGAAAAAAAAACAAATAAAAGAAAAAAAAAAAAAAAAAAAACACAAAAAAAAAAATAAAAAAAAAAAAAAAAACAAAATAAACAAAAACAAAGAAAAAAACAAACAAAAAAAAAAAACAAAAAACTAAAAACAAAAAAAAAACAAAACACAAAAAAAAAAAAAAATAAAAAAAAAACAAAAAAACAAAAAGGAAAAAAAAAAAAGAACAAAAAAAAAAACAACAGAAAAAAGAAAAGAAAAAAAAAAAAAGACCACAAAATAAAAAAAAACAACAAACAAAAAAAAACAAAACAAAAAAACGAACAAAAAAAACAAAAACAAAAAAAAAAAAAAAAAAAAAAAGGCAAAAACAAAAAAAACAAAACAAAACAAAAAAACAAAAAAAAATTAAGATAAAGAACAAAAAAAGAAGAGAAAAAATTAACAAAAAAAAAAAAATAAAAAATACAAAAAGAAATAAAAAATACAACACACAACAAAAACGAAAAAAAAAAAAAAAACACAAAATAGAAAAAAAAAAAAAACAAAAAAAAAAAAAAGAAAAAAACAAAAAAAAAAAAATAAAAAAAAACGACACAGAAACAAAAAATAACAAAAAAAAAAAAAATAAAAAAAAAACAAAAAAAAAACAAAAAATAAAAAAAAAAACAAACAAAAAAAAAAAAAAAATAAAAAAAAAAAAAGCAAAACATAAACAAGAAAAAAAAAAAAAGTACAAATAACAAAACAAAAAAGACACTAAAAAAAAAAAAAAAAAACAAAAAAAAAAAAAAAAAAAAAAAAAGAAAAAAAACCACAAAACAAAAAAATAAAGCAAAAAAAAAAAAAAAAAAAAAAAAAAAATAAATGAAAAAAAAAAGAAAACCAAAAAAATAAAAGA"
    val result2 = steadyGene(gene2)
    printWriter.println(result2)
      assert(result2 == 1393)
      */

    val gene3 = "TGATGCCGTCCCCTCAACTTGAGTGCTCCTAATGCGTTGC"
    val result3 = steadyGene(gene3)
    printWriter.println(result3)
    assert(result3 == 5)

    printWriter.close()
  }
}
