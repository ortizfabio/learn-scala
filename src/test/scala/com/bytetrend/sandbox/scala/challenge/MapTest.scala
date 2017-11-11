package com.bytetrend.sandbox.scala.challenge

import com.bytetrend.sandbox.scala.challenge.MapImplementation.{map, map2}
import org.scalatest.{FunSpec, Matchers}

/**
  * Simple test that verifies the validity of the custom
  * map function.
  */
class MapTest extends FunSpec with Matchers{

  describe("The tail recursive map function should return an new list with elements transformed by f()"){

    it("Tail Recursive map Should double the elements of list (1, 2, 3, 4)"){
      val l2 = List(2,4,6,8)
      map[Int,Int](List(1,2,3,4), x => 2 * x ) shouldBe l2
    }
  }


  it("Tail Recursive map An empty list should return another empty list"){
    val l2:List[Int] = Nil
    map[Int,Int](List(), x => 2 * x ) shouldBe l2
  }

  describe("The  map function should return an new list with elements transformed by f()"){

    it("Should double the elements of list (1, 2, 3, 4)"){
      val l2 = List(2,4,6,8)
      map2[Int,Int](List(1,2,3,4), x => 2 * x ) shouldBe l2
    }
  }


  it("An empty list should return another empty list"){
    val l2:List[Int] = Nil
    map2[Int,Int](List(), x => 2 * x ) shouldBe l2
  }

}

