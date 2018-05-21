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

    val pairs = Array((2,5), (100,200), (15,22),(4,16), (1, 25)).sortBy(x => x._1)


    val result = pairs.foldLeft(List[(Int,Int)]())((list,pair) =>{
      list match {
        case Nil => pair :: list
        case head :: tail  => {
          if( head._2 > pair._1)
            if( head._2 < pair._2)
              (head._1,pair._2) :: tail
            else
              list
          else
            pair :: list

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
