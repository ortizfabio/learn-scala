package com.bytetrend.sandbox.scala.collection

/*
abstract class ObservableSet[E](s: Set[E]) extends ForwardingSet[E](s) {

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

  private def notifyElementAdded(element: E): Unit = {
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

}
*/