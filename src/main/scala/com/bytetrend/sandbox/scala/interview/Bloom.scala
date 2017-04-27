package com.bytetrend.sandbox.scala.interview

/**
  * Create a container class of unique strings that has 3 operations
  * add, remove and getRandom (returning random element)
  * Those operations need to have efficiency  O(1)
**/
object Bloom extends App {

  // Container of unique strings
  // - add(str)
  // - remove(str)
  // - getRandom() -> str
  // Focus -> performance


  class Container {
    val myMap = scala.collection.mutable.HashMap[String, Int]()
    var myIndex = 0
    val myArray = scala.collection.mutable.ArrayBuffer[String]()

    def add(s: String) = {
      myMap += (s -> myIndex)
      myArray.insert(myIndex,s)
      myIndex = myIndex + 1
    }

    def remove(s: String) = {

      val t:Option[Int] = myMap.remove(s)
      myArray(t.get) = ""
    }

    def getRandom(): String = {
      var i = -1
      var str = ""
      while (str == "" && i < myArray.size) {
        val next = scala.util.Random.nextInt(myIndex)
        str = myArray(next)
        i += i + 1
      }
      str
    }

    override def toString() = myMap.mkString("[",",","]")
  }

  val container = new Container
  val list = List[String]("One","Two","Three","Four","Five","Six","Seven","Eight","Nine","Ten")
  for( s <- list) container.add(s)
  println(container.toString())
  println(container.getRandom())
  println(container.getRandom())
  container.remove("Four")
  container.remove("Six")
  container.remove("One")
  container.remove("Three")
  container.remove("Two")
  container.remove("Ten")
  println(container.getRandom())
  println(container.toString())
}

