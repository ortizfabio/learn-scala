package com.bytetrend.sandbox.scala.challenge



class ContainerWithO1Access {
  /**
    * Strings are stored in this map and an integer index is
    * assigned which it is the location in the complement array.
    * The complement array with the index allows the getRandom method
    * to be accessed in O(1) <Bloomberg>
    *
    */
  val myMap = scala.collection.mutable.HashMap[String, Int]()
  val myArray = scala.collection.mutable.ArrayBuffer[Option[String]]()
  val availableIndexes = collection.mutable.Stack[Int]()

  /**
    * This method adds an string to this collection with a Omega of O(1)
    *
    * @param s
    */
  def add(s: String) = {
    //first we need to make sure it does not exist
    if (myMap.get(s) == None) {
      availableIndexes.headOption match {
        case Some(i) => {
          //If there is an available index in the stack we reuse it
          //to allocate the string in the array myArray. This will
          //also remove it from the stack.
          val j = availableIndexes.pop
          myMap += (s -> j)
          myArray.insert(j, Some(s))
        }
        case _ => {
          //There is no available indexes increase myIndex and use it
          //This will grow myArray size.
          val myIndex = myArray.size
          myMap += (s -> myIndex)
          myArray += Some(s)
          assert(myIndex + 1 == myArray.size)
        }
      }
    }
  }

  def remove(s: String): Boolean = {
    val opt: Option[Int] = myMap.remove(s)
    opt match {
      case Some(index) => {
        //If the string exist in myMap we take the associated index
        //and also remove it from myArray and add it to the availableIndexes.
        myArray(index) = None
        availableIndexes.push(index)
        true
      }
      case None => false
    }
  }

  def getRandom(): Option[String] = {
    var i = 0
    var next = scala.util.Random.nextInt(myArray.size)
    var str: Option[String] = None
    while (str == None && i < myArray.size) {
      str = myArray(next)
      i += i + 1
      val size = myArray.size
      next match {
        case `size` => next = 0
        case _ => next = next + 1
      }
    }
    str
  }

  def indexes = availableIndexes

  override def toString() = myMap.mkString("[", ",", "]")
}

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

  object container extends ContainerWithO1Access
  val list = List[String]("One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten")
  for (s <- list) container.add(s)
  println(container.toString())
  println(container.getRandom())
  println(container.getRandom())
  container.remove("Four")
  container.remove("Six")
  container.remove("One")
  container.remove("Three")
  container.remove("Two")
  container.remove("Ten")
  println(s"Available indexes ${container.indexes.mkString(",")}")
  container.add("Eleventh")
  container.add("Twelve")
  container.add("Thirteen")
  container.add("Fourteen")
  container.add("Fifteen")
  println(s"Available indexes ${container.indexes.mkString(",")}")
  println(s"Random value = ${container.getRandom()}")
  println(s"Random value = ${container.getRandom()}")
  println(s"Random value = ${container.getRandom()}")
  println(s"Random value = ${container.getRandom()}")
  println(s"Container's content = ${container.toString()}")
}

