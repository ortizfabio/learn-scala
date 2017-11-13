package com.bytetrend.sandbox.scala.challenge

import java.lang.Math.max

import scala.collection.mutable.ArrayBuffer

/**
  * http://practice.geeksforgeeks.org/problems/exchange-the-leaf-nodes/1
  * http://www.geeksforgeeks.org/swap-nodes-binary-tree-every-kth-level/
  */
object BinaryTreeRotation extends App {

  case class Node(value: Int, left: Option[Node], right: Option[Node])

  def swapNodeBranches(root:Option[Node]) : Option[Node] = {
    root match {
      case None => root
      case Some(node) => val n = Some(Node(node.value,swapNodeBranches(node.right),swapNodeBranches(node.left)))
        n
    }
  }


  def prettyPrint(buffer: ArrayBuffer[Array[Int]], levels: Int, maxItemCount: Int, maxItemWidth: Int, padding: Int): Unit = {
    val maxLineCharWidth = (maxItemCount - 1) * (maxItemWidth + padding)

    def formatInt(i: Int, len: Int): String = {
      val s = s"%-${maxItemWidth}s%${padding}s".format(i, " ")
      val width = (maxLineCharWidth / len) - s.length
      (" " * (width / 2)) + s + (" " * (width - (width / 2)))
    }

    val result: Seq[String] = for {
      row <- buffer
    } yield row.foldLeft("")({
      (str, i) =>
        //       val format = s"%-${maxItemWidth + padding}s"
        //       val x: String = format.format(i)
        str + formatInt(i, row.length)
    })

    result.foreach(println)
  }

  def makeTree(data: Array[Array[Int]]): Node = {
    val nodeArray: Array[Array[Node]] = Array.ofDim(data.length)
    val reversed = data.reverse
    for {
      i <- reversed.indices
      j <- reversed(i).indices
    } yield {
      if (nodeArray(i) == null)
        nodeArray(i) = Array.ofDim(reversed(i).length)
      nodeArray(i)(j) = Node(reversed(i)(j),
        if (i - 1 >= 0 && j * 2 < nodeArray(i - 1).length)
          Some(nodeArray(i - 1)(j * 2))
        else
          None,
        if (i - 1 >= 0 && (j * 2 + 1) < nodeArray(i - 1).length)
          Some(nodeArray(i - 1)(j * 2 + 1))
        else
          None)
    }
    nodeArray(nodeArray.length - 1)(nodeArray(nodeArray.length - 1).length - 1)
  }

  def arrayToTree(array: Array[Int]): ArrayBuffer[Array[Int]] = {
    var levels = 0
    var totalItems = 0
    var itemsLastLevel = 1
    val buffer: ArrayBuffer[Array[Int]] = ArrayBuffer()
    while (totalItems < array.length) {
      //buffer(levels) = Array.ofDim(itemsLastLevel)
      buffer += array.slice(totalItems, totalItems + itemsLastLevel)
      totalItems = totalItems + itemsLastLevel
      if (totalItems < array.length) {
        itemsLastLevel = itemsLastLevel * 2
        levels = levels + 1
      }
    }
    //= Array.fill[List[Int]](levels)( List.empty[Int])
    val maxItemCount = max(buffer(levels).length, max(0, buffer(levels - 1).length))
    val maxIntWidth = array.foldLeft(0)((maxSoFar, i) => if (i.toString.length > maxSoFar) i.toString.length else maxSoFar)
    prettyPrint(buffer, levels, maxItemCount, maxIntWidth, 1)
    //   buffer.foreach((x: Array[Int]) => println(x.mkString("[", ",", "]")))
    //    println(s"levels=$levels items on last level: $itemsLastLevel maximum width: ${maxItemCount} max item width: $maxIntWidth")
    buffer
  }

  val tree: ArrayBuffer[Array[Int]] = arrayToTree(Array(0, 3, 1, 4, 5, 6, 7, 8, 3, 10, 20, 4, 2, 8, 6, 49))
  val root = makeTree(tree.toArray)
  println(root)
  val swapTree = swapNodeBranches(Some(root))
  println(swapTree)
}
