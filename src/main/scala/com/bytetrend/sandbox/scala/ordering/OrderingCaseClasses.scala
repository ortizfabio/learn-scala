package com.bytetrend.sandbox.scala.ordering

/**
  * http://stackoverflow.com/questions/5715568/using-pattern-matching-for-ordered-compare-in-scala
  */
object OrderingCaseClasses extends App {

  case class Person(firstName: String, lastName: String, age: Int)

  implicit val personOrdering = new Ordering[Person] {
    def compare(first: Person, second: Person): Int = {
      second match {
        case Person(_, thatLastName, _) if first.lastName < thatLastName => -1
        case Person(_, thatLastName, _) if first.lastName > thatLastName => 1

        case Person(thatFirstName, _, _) if first.firstName < thatFirstName => -1
        case Person(thatFirstName, _, _) if first.firstName > thatFirstName => 1

        case Person(_, _, thatAge) => first.age compare thatAge
      }
    }
  }

  val persons = List(Person.curried("John")( "Smith")( 35), Person.tupled("John", "Steward", 20), Person("Mary", "Best", 33), Person("John", "Steward", 55))
  //This one will used the personOrdering above declared implicit
  println("sorted "+persons.sorted)

  { //We need to enclose this in a block to be able to override the previous
    //defined implicit otherwise it gives an ambiguous implicit error.
    implicit val personDescending = Ordering[Person].reverse
    println("sorted descending " + persons.sorted(personDescending))
  }

  println("sortWith last name "+persons.sortWith(_.lastName < _.lastName))

}
