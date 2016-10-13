package com.bytetrend.sandbox.scala.collection

import java.util.{Collection, Iterator, Set}

/**
 * Created by db2admin on 5/13/2016.
 */
class ForwardingSet[E](s: Set[E]) extends Set[E] {



  private val set: Set[E] = s;



  override def clear: Unit = { set.clear() }



  override def contains(o: Object): Boolean = { set.contains(o) }



  override def isEmpty: Boolean = { set.isEmpty() }



  override def size: Int = { set.size() }



  override def iterator: Iterator[E] = { set.iterator() }



  override def add(e: E): Boolean = { set.add(e) }



  override def remove(o: Object): Boolean = { set.remove(o) }



  override def containsAll(c: Collection[_]): Boolean = { set.containsAll(c) }



  override def addAll(c: Collection[_ <: E]): Boolean = { set.addAll(c) }



  override def removeAll(c: Collection[_]): Boolean = { set.removeAll(c) }



  override def retainAll(c: Collection[_]): Boolean = { set.retainAll(c) }



  override def toArray: Array[Object] = { set.toArray() }



  override def toArray[E](t: Array[E with AnyRef]): Array[E with AnyRef] = { set.toArray[E](t) }



  override def equals(o: Any): Boolean = { set.equals(o) }



  override def hashCode: Int = set.hashCode()



  override def toString: String = set.toString()



}
