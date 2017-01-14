package com.bytetrend.sandbox.scala.matching

/**
  * Created by db2admin on 1/13/2017.
  */
object MatchExamples extends App {

  case class Person(firstName: String, lastName: String)

  def collectionMatching(col: Seq[Any]): String = {
    col match {
      case list@List(1, _*) => s"a list beginning with 1, having any number of elements $list"
      case Vector(1, _*) => "a vector starting with 1, having any number of elements"
      case list@List(0, _, _) => s"a three-element list with 0 as the first element $list"
      case Vector(1, _*) => "a vector starting with 1, having any number of elements"
      case list: List[_] => s"it is a list $list"
      case _ => "Unknown"
    }
  }

  def nonSeqCollection(col: Any): String = {
    col match {
      case map: Map[String, Int] => s"it is a map $map"
      case array: Array[Int] => s"it is a array $array"
      case _ => "unknown"
    }
  }

  def tupleMatching(x: Any): String = {
    x match {
      case (a, b, c) => s"3-elem tuple, with values $a, $b, and $c"
      case (a, b, c, _) => s"4-elem tuple: got $a, $b, and $c"
      case _ => "unknown tuple"
    }
  }

  def matchType(x: Any): String = x match {
    case x@List(1, _*) => s"$x" // works; prints the list
    case x@Some(_) => s"$x" // works, returns "Some(foo)"
    case p@Person(first, "Doe") => s"A person $p" // works, returns "Person(John,Doe)"
  }

  val tpl = (1, 5, "X", 'c', List(1, 2, 3))

  println(tupleMatching(tpl))
  val v = Vector(1, 2, 4)
  println(collectionMatching(v))
  val m: Map[String, Int] = Map[String, Int]("x" -> 1, "c" -> 10)
  println(nonSeqCollection(m))

  val p = Person("John", "Doe")
  println(matchType(p))

}


object CaseClassTest extends App {

  trait Animal

  case class Dog(name: String) extends Animal

  case class Cat(name: String) extends Animal

  case object Woodpecker extends Animal

  def determineType(x: Animal): String = x match {
    case Dog(moniker) => "Got a Dog, name = " + moniker
    case _: Cat => "Got a Cat (ignoring the name)"
    case Woodpecker => "That was a Woodpecker"
    case _ => "That was something else"
  }

  println(determineType(new Dog("Rocky")))
  println(determineType(new Cat("Rusty the Cat")))
  println(determineType(Woodpecker))
}

object GuardExpression extends App {

  def guardMatch(x: Int) = {
    x match {
      case a if 0 to 9 contains a => println("0-9 range: " + a)
      case b if 10 to 19 contains b => println("10-19 range: " + b)
      case c if 20 to 29 contains c => println("20-29 range: " + c)
      case _ => println("Hmmm...")
    }
  }

  println(guardMatch(1))
  println(guardMatch(11))
  println(guardMatch(29))

}

