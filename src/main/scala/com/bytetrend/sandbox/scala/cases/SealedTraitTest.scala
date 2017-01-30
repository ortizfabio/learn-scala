package com.bytetrend.sandbox.scala.cases

/**
  * http://underscore.io/blog/posts/2015/06/02/everything-about-sealed.html
  */
object SealedTraitTest {

  sealed trait List1[+A] {
    // lotsa methods in here ...
  }

  final case class ::[A](head: A, tl: List1[A]) extends List1[A]

  final case class Nil() extends List1[Nothing]

  def test[A](l: List1[A]): Boolean = {
    l match {
      case ::(_, _) => true
      case Nil() => false
    }
  }

  Option(1) match {
    case None => "Yeah"
    case Some(x) => s"$x"
  }

  sealed trait Base

  final case class SubtypeOne(a: Int) extends Base

  final case class SubtypeTwo(b: Option[String]) extends Base

  //Matching on subtype gives no error.
  SubtypeTwo(Some("oops")) match {
    case SubtypeTwo(None) => "Yeah!"
  }
  //Matching on super type it does.
  (SubtypeOne(1): Base) match {
    case SubtypeOne(a) => a + 2
  }

  //http://stackoverflow.com/questions/35848904/understanding-of-scala-nothing-type
  def test(): Base = ???

  def loop(): Nothing = loop()
}
