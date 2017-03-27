package com.bytetrend.sandbox.scala.collection

/**
  *
  * This is a more general form of fold and reduce. It has similar semantics, but does not require
  * the result to be a supertype of the element type. It traverses the elements in different
  * partitions sequentially, using seqop to update the result, and then applies combop to
  * results from different partitions. The implementation of this operation may operate on
  * an arbitrary number of collection partitions, so combop may be invoked an arbitrary
  * number of times.
  * *
  * For example, one might want to process some elements and then produce a Set. In this case,
  * seqop would process an element and append it to the list, while combop would concatenate
  * two lists from different partitions together. The initial value z would be an empty set.
  * *
  *  pc.aggregate(Set[Int]())(_ += process(_), _ ++ _)
  * *
  * Another example is calculating geometric mean from a collection of doubles (one would typically
  * require big doubles for this). B the type of accumulated results z the initial value for the
  * accumulated result of the partition - this will typically be the neutral element for the seqop
  * operator (e.g. Nil for list concatenation or 0 for summation) and may be evaluated more than
  * once seqop an operator used to accumulate results within a partition combop an associative
  * operator used to combine results from different partitions
  */
object AggregateFunctionExample extends App {

  /**
    *   * http://allaboutscala.com/tutorials/chapter-8-beginner-tutorial-using-scala-collection-functions/scala-aggregate-function/
    * Example of usage of:
    * def aggregate[B](z: =>B)(seqop: (B, A) => B, combop: (B, B) => B): B = foldLeft(z)(seqop)
    * In this tutorial, we went over the following:
    * How to initialize a Set of type String to represent Donut elements
    * How to define an accumulator function to calculate the total length of the String elements
    * How to call aggregate function with the accumulator function from Step 2
    * How to initialize a Set of Tuple3 elements to represent Donut name, price and quantity
    * How to define an accumulator function to calculate the total cost of Donuts
    * How to call aggregate function with accumulator function from Step 5
    */
  println("Step 1: How to initialize a Set of type String to represent Donut elements")
  val donutBasket1: Set[String] = Set("Plain Donut", "Strawberry Donut")
  println(s"Elements of donutBasket1 = $donutBasket1")
  println("\nStep 2: How to define an accumulator function to calculate the total length of the String elements")
  val donutLengthAccumulator: (Int, String) => Int =
    (accumulator, donutName) => {
      accumulator + donutName.length
    }
  println("\nStep 3: How to call aggregate function with the accumulator function from Step 2")
  val z = 0
  type summation[T] = (T, T) => T
  val sumInt: summation[Int] = (x, y) => {
    x * y
  }

  //aggregate[B](z: => B)(seqop: (B, A) => B, combop: (B, B) => B): B = foldLeft(z)(seqop)
  val totalLength = donutBasket1.aggregate(z)(donutLengthAccumulator, sumInt)
  println(s"Total length of elements in donutBasket1 = $totalLength")

  //##############################

  println("\nStep 4: How to initialize a Set of Tuple3 elements to represent Donut name, price and quantity")
  val donutBasket2: Set[(String, Double, Int)] = Set(("Plain Donut", 1.50, 10), ("Strawberry Donut", 2.0, 10))
  println(s"Elements of donutBasket2 = $donutBasket2")
  println("\nStep 5: How to define an accumulator function to calculate the total cost of Donuts")
  type multiplication[U, V] = (U, V) => U
  val mult: multiplication[Double, Int] = (x, y) => x * y
  val sumDouble: summation[Double] = (x, y) => x + y
  val totalCostAccumulator: (Double, Double, Int) => Double = (accumulator, price, quantity) => sumDouble(accumulator, (mult(price, quantity)))
  println("\nStep 6: How to call aggregate function with accumulator function from Step 5")
  val totalCost = donutBasket2.aggregate(0.0)((accumulator: Double, tuple: (String, Double, Int)) => totalCostAccumulator(accumulator, tuple._2, tuple._3), _ + _)
  println(s"Total cost of donuts in donutBasket2 = $totalCost")


}
