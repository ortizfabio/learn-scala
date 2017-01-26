package com.bytetrend.sandbox.scala.interview

object Morgan {

  def winner(events: List[Int]): Int = {

    def findLeaders(map: Map[Int, Int],topCount:Int): List[Int] = {
      val i = for {
        x <- map
        if (x._2 == topCount)
      } yield x._1
      i.toList
    }

    def findTop(l:List[Int],topCount:Int):Int = {
      val t = collection.mutable.Map[Char,Int] ()
      val mapCount:collection.mutable.Map[Int,Int] = l.map({ a => a -> topCount })(collection.breakOut)
      //  val mapCount = collection.mutable.Map(mm: _*)
        val it = events.iterator
        while(it.hasNext){
          val x = it.next
          if(mapCount.contains(x)){
            if(mapCount(x) == 1)
              return x
            else
              mapCount(x) = mapCount(x) - 1
          }
        }
      return -1
    }
    val map : Map[Int,Int] = events.groupBy(identity).mapValues((x: List[Int]) => x.length)
    val topCount = map.values.toList.sorted(Ordering[Int].reverse).head

    val leaders = findLeaders(map,topCount)
    leaders.length match{
      case 1 => leaders.head
      case _ => findTop(leaders,topCount)
    }
  }

  def main(args:Array[String]):Unit ={
    println(winner(List(1,2,3,1)))
    println(winner(List(10,20,30,40)))
    println(winner(List(22,33,44,44,33,10,20,22,44,33,11,9)))
  }
}


object Morgan2 {

  def winner(events: List[Int]): Int = {

    def findTop(l:List[Int],topCount:Int):Int = {

      val mapCount:collection.mutable.Map[Int,Int] = l.map({ a => a -> topCount })(collection.breakOut)
      //  val mapCount = collection.mutable.Map(mm: _*)
      val it = events.iterator
      while(it.hasNext){
        val x = it.next
        if(mapCount.contains(x)){
          if(mapCount(x) == 1)
            return x
          else
            mapCount(x) = mapCount(x) - 1
        }
      }
      return -1
    }


    val map : Map[Int,Int] = events.groupBy(identity).mapValues((x: List[Int]) => x.length)
    val topCount = map.values.toList.sorted(Ordering[Int].reverse).head
    val leaders:List[Int] = map.toList.collect{ case (x,y) if y == topCount => x }

    leaders.length match{
      case 1 => leaders.head
      case _ => findTop(leaders,topCount)
    }
  }

  def main(args:Array[String]):Unit ={
    println(winner(List(1,2,3,1)))
    println(winner(List(10,20,30,40)))
    println(winner(List(22,33,44,44,33,10,20,22,44,33,11,9)))
  }
}

