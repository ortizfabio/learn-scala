package com.bytetrend.sandbox.scala.algo


object FindDuplicatesInAList extends App{

 def dumb(dup:List[Int])= {
    val distinct:List[Int] = dup.distinct
    println(distinct)
    val elementsWithCounts:List[(Int, Int)] = distinct.map((a:Int) => (a, dup.count((b:Int) => a == b )) ).filter( (pair: (Int,Int) ) => { pair._2 > 1 } )
    println(elementsWithCounts)
  }

  def smart(dup:List[Int]) = {
    println(dup.groupBy(identity).collect { case (x, List(_,_,_*)) => x }.map(x => (x,dup.count(y=> x == y))))
    //lengthCompare does not process the whole list it only checks that is greater than 1
    //short circuiting once it is found to be true.
    println(dup.groupBy(identity).collect { case (x,ys) if ys.lengthCompare(1) > 0 => x })
  }
  val dup = List(1,1,1,2,3,4,5,5,6,100,101,101,102)
  smart(dup)
  dumb(dup)
}
