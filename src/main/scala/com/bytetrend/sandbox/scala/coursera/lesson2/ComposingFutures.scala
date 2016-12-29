package com.bytetrend.sandbox.scala.coursera.lesson2

import scala.concurrent.Future

//Lecture 4.10 Composing Futures 2
//Retrying to send using foldRight

trait ComposingFutures[T] {


 def retryRight(noTimes: Int)(block: => Future[T]):Future[T] ={
   val ns:List[Int] = (1 to noTimes).toList
   val attempts:List[() => Future[T]] = ns.map(_ => () => block)
   val failed:Future[Nothing] = Future.failed(new Exception)
   val result:((() => Future[T], () => Future[Nothing]) => () => Future[Nothing]) => () => Future[Nothing]
   = attempts.foldRight(() => failed)
   ((block:Future[T], a:List[() => Future[T]] ) => () => { block.fallbackTo{ a() }})
   result()
 }

 def retryLeft(noTimes: Int)(block: => Future[T]):Future[T] = {
   val ns:List[Int] = (1 to noTimes).toList
   val attempts:List[() => Future[T]] = ns.map(_ => () => block)
   val failed:Future[Nothing] = Future.failed(new Exception)
   val result = attempts.foldLeft(failed)
   ((a:List[()=>Future[T]],block:Future[T]) => a recoverWith {block()})
   result()
 }

}
