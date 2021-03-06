package com.bytetrend.sandbox.scala.collection

/**
  * Created by db2admin on 1/17/2017.
  */
object BinaryTree {

  import scala.annotation.meta.getter

  /**
    * https://www.hackerrank.com/challenges/similarpair
    */

  sealed abstract class Tree[T](
                                 @(inline@getter) final val value: T,
                                 @(inline@getter) final val left: Option[Tree[T]],
                                 @(inline@getter) final val right: Option[Tree[T]]) {
    // @(inline @getter) final val count: Int = 1 + LeftNode.count(left) + RightNode.count(right)
    def add(value: T)(implicit ordering: Ordering[T]): Tree[T] = {
      this match {
        case LeftNode(data,None,None) => if(ordering.lt(data,value) == true){
          LeftNode(value,Some(LeftNode(data,None,None)),None)
        }else{
          LeftNode(value,None,Some(RightNode(data,None,None)))
        }
        case RightNode(data,None,None) => if(ordering.lt(data,value) == true){
          RightNode(value,Some(LeftNode(data,None,None)),None)
        }else{
          RightNode(value,None,Some(RightNode(data,None,None)))
        }
        case LeftNode(data,Some(left),None) => if(ordering.lt(data,value) == true){
          LeftNode(value,Some(LeftNode(data,Some(left),None)),None)
        }else{
          LeftNode(data,Some(add(value)),Some(left))
        }
        case LeftNode(data,None,Some(right)) => if(ordering.lt(data,value) == true){
          LeftNode(data,Some(LeftNode(right.value,right.left,right.right)),Some(RightNode(value,None,None)))
        }else{
          LeftNode(data,Some(LeftNode(value,None,None)),Some(RightNode(right.value,right.left,right.right)))
        }
        case RightNode(data,Some(left),None) => if(ordering.lt(data,value) == true){
          LeftNode(data,Some(left),Some(RightNode(value,None,None)))
        }else{
          LeftNode(data,Some(add(value)),Some(left))
        }
        case RightNode(data,None,Some(right)) => if(ordering.lt(data,value) == true){
          LeftNode(data,Some(LeftNode(right.value,right.left,right.right)),Some(RightNode(value,None,None)))
        }else{
          LeftNode(data,Some(LeftNode(value,None,None)),Some(RightNode(right.value,right.left,right.right)))
        }

      }
    }
  }

  final class LeftNode[T](value: T,
                          left: Option[Tree[T]],
                          right: Option[Tree[T]]) extends Tree[T](value, left, right) {

  }

  object LeftNode {
    @inline def apply[T](value: T, left: Option[Tree[T]], right: Option[Tree[T]]) = new LeftNode(value, left, right)

    def unapply[T](t: LeftNode[T]) = Some((t.value, t.left, t.right))

  }

  final class RightNode[T](value: T,
                           left: Option[Tree[T]],
                           right: Option[Tree[T]]) extends Tree[T](value, left, right) {
  }

  object RightNode {
    @inline def apply[T](value: T, left: Option[Tree[T]], right: Option[Tree[T]]) = new RightNode(value, left, right)

    def unapply[T](t: RightNode[T]) = Some((t.value, t.left, t.right))
  }

}
