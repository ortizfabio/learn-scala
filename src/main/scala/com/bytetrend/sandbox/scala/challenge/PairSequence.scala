package com.bytetrend.sandbox.scala.challenge

/*
Design an algorithm and write code to identify the overlapping intervals for
the following sequence of pairs:
{[2, 5], [100, 200], [15, 22], [4, 16]}.

The output should be: {[2, 22], [100, 200]}

  2   4  5      15  16   22         100    200
--+---+--+------+---+----+-----------+------+----

*/
object PairSequence {
  def main(args: Array[String]) = {
    /* we know that (a,b) a > b then we sort the pair therefore we know p1.a >= p2.a
    and we only need to compare the second element of the pair.
     */
    val pairs = Array((2,5), (100,200), (15,22),(4,16), (1, 25)).sortBy(x => x._1)
    val result = pairs.foldLeft(List[(Int,Int)]())((list,pair) =>{
      list match {
        case Nil => pair :: list
        case head :: tail  => {
          if( head._2 > pair._1)
            if( head._2 < pair._2)
              (head._1,pair._2) :: tail //head's end value is in between pair start and end
            else
              list  //current pair is encircled in head start and end
          else
            pair :: list //pair is after the end of the head

        }
      }
    })
    println(result.reverse.mkString(","))

  }
}
// 3,12
//(2,5) ,(4,10)
// (3,12),(4,10)
// (3.12)
