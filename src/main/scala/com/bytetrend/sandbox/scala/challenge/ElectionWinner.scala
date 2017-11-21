package com.bytetrend.sandbox.scala.challenge

/**
  * <Morgan>
  */
object ElectionWinner {

  def winner(events: List[Int]): Int = {

    /**
      * This function is called when there is more than one element in the list with the
      * same top count. This function returns the id of the first one to complete the
      * top count.
      *
      * @param map
      * @return
      */
    def findTop(map: collection.mutable.Map[Int, Int]): Int = {
      val it = events.iterator
      while (it.hasNext) {
        val x = it.next
        if (map.contains(x)) {
          if (map(x) == 1)
            return x
          else
            map(x) = map(x) - 1
        }
      }
      return -1
    }

    // convert to map with Id and count of ids appearing in list
    val map: Map[Int, Int] = events.groupBy(identity).mapValues((x: List[Int]) => x.length)
    // find the id with the higher count.
    val topCount = map.values.max
    // find the ids that have a count of maximum from 1 to n it uses braces to indicate
    // a function rather than parameters.
    val leaders: Map[Int, Int] = map.filter { case (k, v) if (v < topCount) => false; case _ => true }
    //There are two cases when there is only one with the top count and when there is
    //multiple ones. In the later case we need to find out the one that completed first.
    leaders.size match {
      case 1 => leaders.head._1
      case _ => findTop(collection.mutable.Map(leaders.toSeq: _*))
    }
  }

  def main(args: Array[String]): Unit = {
    //In this case the winner should be 1 because there are 2 of them
    println(winner(List(1, 2, 3, 1)))
    //In this case all have count of 1 but 10 is the first to appears so that is the winner.
    println(winner(List(10, 20, 30, 40)))
    //In this case we have 2 with count of 3 33 and 44. The first one to complete 3 is
    // 44 so that is the winner.
    println(winner(List(22, 33, 44, 44, 33, 10, 20, 22, 44, 33, 11, 9)))
  }
}


object ElectionWinner2 {

  def winner(events: List[Int]): Int = {

    def findTop(l: List[Int], topCount: Int): Int = {

      val mapCount: collection.mutable.Map[Int, Int] = l.map({ a => a -> topCount })(collection.breakOut)
      //  val mapCount = collection.mutable.Map(mm: _*)
      val it = events.iterator
      while (it.hasNext) {
        val x = it.next
        if (mapCount.contains(x)) {
          if (mapCount(x) == 1)
            return x
          else
            mapCount(x) = mapCount(x) - 1
        }
      }
      return -1
    }


    val map: Map[Int, Int] = events.groupBy(identity).mapValues((x: List[Int]) => x.length)
    val topCount = map.values.toList.sorted(Ordering[Int].reverse).head
    val leaders: List[Int] = map.toList.collect { case (x, y) if y == topCount => x }

    leaders.length match {
      case 1 => leaders.head
      case _ => findTop(leaders, topCount)
    }
  }

  def main(args: Array[String]): Unit = {
    println(winner(List(1, 2, 3, 1)))
    println(winner(List(10, 20, 30, 40)))
    println(winner(List(22, 33, 44, 44, 33, 10, 20, 22, 44, 33, 11, 9)))
  }
}

