package com.bytetrend.sandbox.scala.collection

import java.util.{Collection, Set}

import scala.reflect.ClassTag

/**
 * Created by db2admin on 5/13/2016.
 */
class ObservableSet[E](s: Set[E]) extends ForwardingSet[E](s) {
  import java.util.List
  import java.util.concurrent.CopyOnWriteArrayList

  final private val observers: List[SetObserver[E]] = new CopyOnWriteArrayList[SetObserver[E]];

  def addObserver(observer: SetObserver[E]): Unit = {
    observers.add(observer)
  }

  def removeObserver(observer: SetObserver[E]): Unit = {
    import scala.collection.JavaConversions._
    observers.foreach { x => observers.remove(x); }
  }

  private def notifyElementAdded(element: E): Unit =  {
    import scala.collection.JavaConversions._
    for (observer <- observers)
    observer.added(this, element)
  }

  //
  //  def addObserver(observer: SetObserver[E]): Unit = observers.synchronized {
  //    observers.add(observer)
  //  }
  //
  //  def removeObserver(observer: SetObserver[E]): Unit = observers.synchronized {
  //    import scala.collection.JavaConversions._
  //    observers.foreach { x => observers.remove(x); }
  //
  //  }
  //
  //  private def notifyElementAdded(element: E): Unit = observers.synchronized {
  //    import scala.collection.JavaConversions._
  //    for (observer <- observers)
  //    observer.added(this, element)
  //  }
  //

  override def add(element: E): Boolean = {
    val added: Boolean = super.add(element)
    if (added)
      notifyElementAdded(element)
    added
  }

  override def addAll(c: Collection[_ <: E]): Boolean = {
    var result: Boolean = false
    import scala.collection.JavaConversions._
    for (element <- c) {
      result |= add(element)
    }
    result
  }

  /**
    * https://www.chrisstucchio.com/blog/2014/cannot_find_classtag_for_element_type_T.html
    * new Array[T] needs ClassTag implicit
    *
    * @param a
    * @param m
    * @tparam T
    * @return
    */
   def toArray[T](a: Array[T])(implicit m: ClassTag[T]): Array[T] = {
    val b =  new Array[T](a.length)
    a copyToArray b
    b
  }
}
