package com.bytetrend.sandbox.scala.cases


object CaseClassesExamples {

  case class Card(suit: String, rank: Int)

  val c1 = Card("Spades", 2)

  val c2 = Card.tupled("Diamonds", 10)

  val c3 = Card.curried("Clubs")(4)

}
