package com.bytetrend.sandbox.scala.collection

object TestSequence {
  val nonEmptySeq = Seq(1,2,3,4,5)
  val emptySeq = Seq.empty[Int]
  val nonEmptyList = List(1,2,3,4,5)
  val emptyList = Nil
  val nonEmptyVector = Vector(1,2,3,4,5)
  val emptyVector = Vector.empty[Int]
  val nonEmptyMap = Map("one" -> 1, "two"->2, "three" -> 3)
  val emptyMap = Map.empty[String,Int]

  def seqToString[T](localSeq: Seq[T]): String = localSeq match {
    case head +: tail => s"$head +: " + seqToString(tail)
    case Nil => "Nil"
  }

  def listToString[T](list: List[T]): String = list match {
    case head :: tail => s"($head :: ${listToString(tail)})" //
    case Nil => "(Nil)"
  }

  def main(args: Array[String]): Unit = {

    for(seq <- Seq(nonEmptySeq, emptySeq,nonEmptyList,emptyList,
      nonEmptyMap.toSeq,emptyMap.toSeq,nonEmptyVector, emptyVector)){
      println(seqToString(seq))
//      println(listToString(seq))
    }
  }

}
