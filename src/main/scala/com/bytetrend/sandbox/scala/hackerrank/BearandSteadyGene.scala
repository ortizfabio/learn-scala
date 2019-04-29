package com.bytetrend.sandbox.scala.hackerrank



import java.io.PrintWriter

object BearandSteadyGene {




  // Complete the steadyGene function below.
  def steadyGene(gene: String): Int = {
    import scala.collection.mutable.{Map => MMap}
    val map: MMap[Char,Int] =  MMap('C' -> 0, 'G' -> 0, 'A' -> 0, 'T' -> 0)
    for ( c <- gene){
      map(c) = map(c) + 1
    }
    val perLetterCount = gene.length / 4
    val f:MMap[Char, (Int,Int)] = for ((k,v) <- map) yield (k -> (perLetterCount - v,v/perLetterCount))
    val k = f.map{ case(c,(x,y)) => (c -> (perLetterCount * y - x) ) }
    f.foreach(println)
   // f.values.toSeq.flatmap(x => x).size
0
  }

  def main(args: Array[String]) {
    val stdin = scala.io.StdIn

    val printWriter = new PrintWriter(System.out)

    // A=-4
    // A=0 c=2 T=1 G=1
    val gene = "GAAATAAA"

    val result = steadyGene(gene)

    printWriter.println(result)

    printWriter.close()
  }
}
