package com.bytetrend.sandbox.scala

import scala.collection.immutable.IndexedSeq

/**
  * Created by db2admin on 9/18/2016.
  */
class Pouring(capacity: Vector[Int]) {
  //States
  type State = Vector[Int]
  val initialState:State = capacity map (x => 0)

  //Moves
  trait Move {
    def change(state: State):State
  }
  case class Empty(glass: Int) extends Move{
    def change(state: State)= state updated(glass,0)
  }
  case class Fill(glass: Int) extends Move{
    def change(state: State)= state updated (glass,capacity(glass))
  }
  case class Pour(fromGlass: Int, toGlass: Int)extends Move{
    def change(state: State)= {
      val amount = state(fromGlass) min (capacity(toGlass) - state(toGlass))
      state updated(fromGlass , state(fromGlass)-amount)
      state updated(toGlass,state(toGlass)+amount)
    }
  }

  val glasses = 0 until capacity.length

  val moves:IndexedSeq[Move] =
    (for (g <- glasses) yield Empty(g)) ++
    (for (g <- glasses) yield Fill(g)) ++
      (for(from <- glasses; to <- glasses if from != to) yield Pour(from, to))

  class Path(history: List[Move], val endState:State){
    def extend(move: Move):Path= new Path(move :: history, move change endState)
    override def toString = (history.reverse mkString " ") + "--> " + endState

//    def endState: State = trackState(history)
//    private def trackState(xs: List[Move]): State = xs match {
//      case Nil => initialState
//      case move :: xs1 => move change trackState(xs1)
//    }
  }

  val initialPath = new Path(Nil, initialState)

  def from(paths: Set[Path], explored: Set[State]):Stream[Set[Path]]=
  if(paths.isEmpty)Stream.empty
  else {
    val more:Set[Path] = for {
      path <- paths
      next <- moves map path.extend
      if !(explored contains next.endState)
    }yield  next
    paths #:: from(more, explored ++ (more map (_.endState)))
  }

  val pathSets:Stream[Set[Path]] = from(Set(initialPath),Set(initialState))

  def solutions(target: Int): Stream[Path] =
  for{
    pathSet <- pathSets
    path <- pathSet
    if path.endState contains target
  } yield  path
}
