package com.bytetrend.sandbox.scala.parallel

import org.scalacheck.Prop.forAll
import org.scalacheck.{Gen, Properties}
import org.scalacheck.util.Buildable._

import scala.collection.generic.CanBuildFrom
import scala.collection.immutable.List
import scala.reflect.ClassTag
//import util.Buildable._

import com.bytetrend.sandbox.scala.concurrent.pnorm._
import org.scalacheck.util.Buildable

import scala.collection.mutable
/**
  * Created by jose.ortiz on 12/31/16.
  */
object PnormChecker extends Properties("p-norm") {


  implicit val ArrayCanBuildFrom: CanBuildFrom[String, Char, String] = new CanBuildFrom[String, Char, String] {
    def apply(from: String) = apply()
    def apply()             = mutable.StringBuilder.newBuilder
  }

  implicit def buildableArray[T:ClassTag] = new Buildable[T,Array[T]] {
    import scala.collection.mutable.ArrayBuffer
    def builder = new mutable.Builder[T,Array[T]] {
      val al = new ArrayBuffer[T]
      def +=(x: T) = {
        al += x
        this
      }
      def clear() = al.clear()
      def result() = al.toArray
    }
  }

  /** Generates a non-empty list of random length. The maximum length depends
    *  on the size parameter. This method is equal to calling
    *  `nonEmptyContainerOf[List,T](g)`. */
//  def nonEmptyArrayOf[T](g: => Gen[T]) = Gen.nonEmptyBuildableOf[Array[T],T](g)

  val genIntList: Gen[List[Int]] = Gen.nonEmptyListOf[Int](Gen.chooseNum(1, 100)).map(_.sorted)
//  val genArrayInt: Gen[Array[Int]] = nonEmptyArrayOf[Int](Gen.chooseNum(1, 100))

  property("pNorm-vs-pNormTwoPart") = forAll(genIntList) {
    (l: List[Int]) => {
      val a = l.toArray
      val sequential = pNorm(a,2.0)
      val parallel =  pNormTwoPart(a,2.0)
      sequential == parallel
    }
  }

}
