package com.bytetrend.sandbox.scala.collection

/**
 * Created by db2admin on 5/13/2016.
 */
trait SetObserver[E] {

  // Invoked when an element is added to the observable set

  def added(set: ObservableSet[E], element: E)

}
