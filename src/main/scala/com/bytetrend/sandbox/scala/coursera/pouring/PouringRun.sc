package com.bytetrend.sandbox.scala

object test {
  val problem = new Pouring(Vector(4,7))          //> problem  : com.bytetrend.sandbox.scala.Pouring = com.bytetrend.sandbox.scala.
                                                  //| Pouring@5056dfcb

  problem.moves                                   //> res0: scala.collection.immutable.IndexedSeq[com.bytetrend.sandbox.scala.test
                                                  //| .problem.Move] = Vector(Empty(0), Empty(1), Fill(0), Fill(1), Pour(0,1), Pou
                                                  //| r(1,0))

   problem.solutions(6)                           //> res1: Stream[com.bytetrend.sandbox.scala.test.problem.Path] = Stream()
  
}