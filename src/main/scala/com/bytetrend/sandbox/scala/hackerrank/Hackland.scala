package com.bytetrend.sandbox.scala.hackerrank

/**
  * There are n citizens voting in this year's election.
  * Each voter writes the name of their chosen candidate on a ballot and places it
  * in a ballot box. The candidate with the highest number of votes wins the election.
  * if two or more candidates have the same number of votes, then the tied candidates
  * names are ordered alphabetically and the last name wins.
  *
  * Complete the electionWinner function, it has one parameter an array of strings, votes
  * describing the voes in the ballot box. This function must review these votes and
  * return  a string representing the name of the winning candidate.
  *
  * Input Format: a list of names.
  * Output Format: a string denoting the name of the winner.
  */
object Hackland extends App {


  def electionWinner(votes: Array[String]): String = {
    val map: Map[String, Int] = votes.groupBy(identity).mapValues(x => x.length)
    val topCount = map.values.toList.sorted(Ordering[Int].reverse).head
    map.toList.collect { case (x, y) if y == topCount => x }.sorted.reverse.head

  }

  val line1 = Array("Alex", "Michael", "Harry", "Dave", "Michael", "Victor", "Harry", "Alex", "Marry", "Marry")
  val winner1 = electionWinner(line1)
  println(s" Winner must be Michael = $winner1")

  val line2 = Array("Victor", "Veronica", "Ryan", "Dave", "Maria", "Maria", "Farah", "Farah", "Ryan", "Veronica")
  val winner2 = electionWinner(line2)
  println(s" Winner must be Veronica = $winner2")

}
