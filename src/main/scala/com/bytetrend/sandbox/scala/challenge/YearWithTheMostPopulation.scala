package com.bytetrend.sandbox.scala.challenge

import scala.collection.{SortedMap, mutable}

/**
  * Find the year with the higest population given a list of birth and death days
  * https://vimeo.com/158532188 pw=FB_IPS
  */
object SimpleYearWithTheMostPopulation {

  val bdYears = List((1929, 1967), (1939, 1994), (1945, 1985), (1900, 1929), (1929, 1967), (1969, 2002), (1989, 2019))

  def main(args: Array[String]) = {
    import mutable.Map
    val result = Map[Int, Int]().withDefaultValue(0)
    //Double loop loop over all year pairs
    bdYears.foreach(pair => {
      //then loop over the range of years between birth and death
      for (x <- (pair._1 to pair._2)) {
        result(x) += 1
      }
    })

    println(result.toSeq.sortBy(r => (r._2, r._1))(Ordering.Tuple2(Ordering.Int.reverse, Ordering.Int)).take(100))
  }
}

object YearWithTheMostPopulation {

  val bdYears = List((1929, 1967), (1939, 1994), (1945, 1985), (1900, 1929), (1929, 1967), (1969, 2002), (1989, 2019))

  case class ScoreSofar(runningCount: Int, year: Int, totalBirths: Int)

  def main(args: Array[String]) = {
    import mutable.Map
    // For each pair of birth/death create a map of year and count. Add one if birth year
    // substract one if death year. Then mergesort by year ascending.
    val result = bdYears.foldLeft(Map[Int, Int]().withDefaultValue(0))({ (map, pair) => {
      map(pair._1) += 1
      map(pair._2) -= 1
      map
    }
    }).toSeq.sortBy(_._1)
    result.foreach(println)
    //triple is a pair of current year sum and a pair of year and maximum count for that year which is
    //the current running count of adding birth and substracting deaths up to that year.
   val triplet = result.foldLeft(ScoreSofar(0, 0, 0))({ (scoreSofar, yearCountPair) => {
      val newBirthCount = scoreSofar.runningCount + yearCountPair._2
      if (newBirthCount > scoreSofar.totalBirths)
        ScoreSofar(newBirthCount, yearCountPair._1, newBirthCount)
      else
        ScoreSofar(newBirthCount, scoreSofar.year, scoreSofar.totalBirths)
    }
    })
    println(s"year: ${triplet.year} births: ${triplet.totalBirths} final count: ${triplet.runningCount}")
    //   println(result.toSeq.sortBy(r => (r._2, r._1))(Ordering.Tuple2(Ordering.Int.reverse, Ordering.Int)).take(100))
  }
}
