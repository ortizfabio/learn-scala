package com.bytetrend.sandbox.scala.interview


import scala.collection.mutable.ListBuffer

/**
  * http://www.spoj.com/problems/WORDS1/
  * Test data https://codeassign.com/groups/13/problems/26
  *
  * Some of the secret doors contain a very interesting word puzzle.
  * The team of archaeologists has to solve it to open that doors. Because
  * there is no other way to open the doors, the puzzle is very important for us.
  *
  * There is a large number of magnetic plates on every door. Every plate
  * has one word written on it. The plates must be arranged into a sequence
  * in such a way that every word begins with the same letter as the previous
  * word ends. For example, the word acm'' can be followed by the word
  * motorola''. Your task is to write a computer program that will read
  * the list of words and determine whether it is possible to arrange all of
  * the plates in a sequence (according to the given rule) and consequently
  * to open the door.
  */
object HeadToTail extends App {
  /*
  Input
  3
  2
  acm
  ibm
  3
  acm
  malform
  mouse
  2
  ok
  ok
1
4
route
error
eat
tire
   */
  def readData(): ListBuffer[(Char, Char)] = {
    //Enter your code here. Read input from STDIN. Print output to STDOUT
    val list = ListBuffer[(Char, Char)]()
    val sc = new java.util.Scanner(System.in)
    val test = sc.nextLine.toInt
    for (i <- 0 until test) {
      val count = sc.nextLine.toInt
      for (j <- 0 until count) {
        val word: String = sc.nextLine()
        list += ((word.charAt(0), word.reverse.charAt(0)))
      }
    }
    list
  }

/** This solution does not work second does */
  def solution(list: ListBuffer[(Char, Char)]) {
    var found = false
    var count = 0
    var ll: ListBuffer[(Char, Char)] = new ListBuffer[(Char, Char)] ++ list
    while (!found && count < list.length) {
      found = solve(ll.head, ll.tail)
      ll.append(ll.head)
      ll = new ListBuffer ++ (ll.tail)
      count = count + 1
    }
    if (found)
      println("Ordering is possible.")
    else
      println("The door cannot be opened.")


    def findMatch(x: (Char, Char), list: ListBuffer[(Char, Char)]): Boolean = {
      list match {
        case list if (list.size == 1) => true
        case _ => {
          list -= x
          solve(x, list)
        }
      }
    }

    def solve(word: (Char, Char), list: ListBuffer[(Char, Char)]): Boolean = {
      println(word)
      val choices = list.filter((x: (Char, Char)) => x._1 == word._2).iterator
      var result = false
      var x: (Char, Char) = null
      while (choices.hasNext && !result) {
        x = choices.next()
        result = findMatch(x, list)
      }
      result match {
        case false =>
          false
        case true => {
          if (list.size == 1) {
            println(x)
            true
          } else {
            list -= x
            solve(list.head, list.tail)
          }
        }
      }
    }
  }

  solution(readData())
}

object HeadToTailTest extends App {

  import HeadToTail.solution
  //Should print "Ordering is possible."
  solution(new ListBuffer() ++ List("route", "error", "eat", "event", "time", "exit", "tuba", "adam").map(x => (x.charAt(0), x.reverse.charAt(0))))
  //Should print "The door cannot be opened."
  solution(new ListBuffer() ++ List("apple", "lion", "nile", "animal").map(x => (x.charAt(0), x.reverse.charAt(0))))
  //Should print "Ordering is possible."
  solution(new ListBuffer() ++ List("river", "error", "eat", "tire").map(x => (x.charAt(0), x.reverse.charAt(0))))

}

/**
  * This solution works
  */
object SecondSolution extends App {

  def solve(words: Array[String]): Unit = {

    var result: List[Int] = List.empty
    //Prime the search by taking the first word and looping thru all unless one is found
    //The solution returned should have a length equal to the list of all words.
    (0 until words.length).takeWhile(i =>  {
      result = run(i)
      result.length != words.length
    })

    if ( result.length == words.length)
      println(s"Ordering is possible. ${result.reverse.foreach(x => print(s" ${words(x)} "))}")
    else
      println("The door cannot be opened.")

    def rebuildMap(char:Char,list:List[Int],bitMap: Map[Char, List[Int]]): Map[Char, List[Int]]={
      val m = (bitMap - (char))
      if(!list.isEmpty)
        m + (char -> list)
      else
        m
    }

    def search(indexList:List[Int],bitMap: Map[Char, List[Int]]): List[Int] = {
      val lastChar = words(indexList.head).reverse.charAt(0)
      bitMap.get(lastChar) match {
        case Some(list) if(list.headOption != None) => {
          var endList = List.empty[Int]
          list.takeWhile(i => {
            endList = search(i :: indexList,rebuildMap(lastChar,list.filter(_!=i),bitMap))
            endList.length != words.length
          })
          endList
        }
        case _ => {
            indexList
        }
      }
    }

    def run(index:Int): List[Int] = {
      val bitMap: Map[Char, List[Int]] = words.zipWithIndex.filter(pair => pair._2 != index).foldLeft(Map.empty[Char, List[Int]])((map, wordIndexPair) => {
        val (word:String,index:Int) = wordIndexPair
        val indexList = map.getOrElse(word.charAt(0), List.empty[Int])
        map + (word.charAt(0) -> ( index :: indexList))
      })
      search(List(index), bitMap)
    }
  }

  solve(Array[String]( "tuba", "error", "route", "exit", "time", "event", "adam"))
  solve(Array[String]("river", "error", "eat", "tire"))
  solve(Array[String]("route", "error", "eat", "event", "time", "exit", "tuba", "adam"))
  solve(Array[String]("apple", "lion", "nile", "animal"))



}