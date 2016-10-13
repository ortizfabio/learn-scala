package com.bytetrend.sandbox.scala.collection

import java.util.{Collection, Set}

/**
 * Created by db2admin on 5/13/2016.
 */
class InstrumentedSet[E](s: Set[E]) extends ForwardingSet[E](s) {



  private var addCount: Int = 0



  override def add(e: E): Boolean = { addCount += 1; super.add(e) }



  override def addAll(e: Collection[_ <: E]): Boolean = { addCount += e.size(); super.addAll(e) }



  def getAddCount: Int = addCount



}
