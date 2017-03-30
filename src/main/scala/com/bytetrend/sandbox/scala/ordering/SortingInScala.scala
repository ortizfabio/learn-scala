package com.bytetrend.sandbox.scala.ordering


object SortingInScala extends App {

  //########### sorted #################
  val listOfInt = List(9, 0, 4, 2, 2, 8, 1)

  println("sorted "+listOfInt.sorted)
  println("sorted "+listOfInt.sorted(Ordering[Int].reverse))

  //############## sortWith ############
  // To use this function, we must pass another function (called "lt"). The argument function, specified as a lambda, must return true if the first argument is less than the second.
  //Here:
  //  We sort a List of strings by their lengths. To come first, a string must have a smaller length (shortest are first).
  //  Scala program that uses sortWith

  // Contains four strings of different lengths.
  val codes = List("abc", "defg", "hi", "jklmn")

  val lt = (x:String, y:String) => x.length() < y.length()
  // Sort list by lengths of strings.
  println("sortWith "+codes.sortWith(_.length < _.length))

  //Sorts Descending because sortWith expects a less than function
  println("sortWith "+listOfInt.sortWith(_ > _))

  //Using String ordering
  println(codes.sortWith(Ordering[String].gteq(_,_)))

  //########### sortBy ###############
  // These ids all have a start char and an ending digit.
  val ids = List("a5", "b0", "z0", "c9", "d9", "d0", "d5")

  //Define a function
  val sortByFunc: (String) => (Char,Char) = (x: String) => (x.charAt(1), x.charAt(0))

  //Create a sort key. The second char is first and the first char second.
  // sortBy calls a function def fromLessThan[T](cmp: (T, T) => Boolean): Ordering[T]
  //it uses the function parameter to build an Ordering with type as the same as the
  //return of the function that has a s compare method using the function.
  //def compare(x: T, y: T) = if (cmp(x, y)) -1 else if (cmp(y, x)) 1 else 0
  //The second implicit parameter to sortBy is (Ordering.Tuple2[Char,Char])
  println("sortBy "+ids.sortBy(sortByFunc))

  import OrderingCaseClasses.Person
  val persons = List(Person.curried("John")( "Smith")( 35), Person.tupled("John", "Steward", 20), Person("Mary", "Best", 33), Person("John", "Steward", 55))
  //sortBy (an attribute)
  println("sortBy "+persons.sortBy(_.age))
}
