package com.bytetrend.sandbox.scala.challenge

import java.lang.Math.max

import scala.collection.mutable.ArrayBuffer

/**
  * http://practice.geeksforgeeks.org/problems/exchange-the-leaf-nodes/1
  * http://www.geeksforgeeks.org/swap-nodes-binary-tree-every-kth-level/
  */
object BinaryTreeRotation extends App {

  case class Node(value: Int, left: Option[Node], right: Option[Node])

  /**
    * Swaps left and right nodes in a recursive way.
    *
    * @param root
    * @return
    */
  def swapNodeBranches(root:Option[Node]) : Option[Node] = {
    root match {
      case None => root
      case Some(node) => val n = Some(Node(node.value,swapNodeBranches(node.right),swapNodeBranches(node.left)))
        n
    }
  }

  /**
    * Prints the binary tree in a hierarchical way.
    *
    * @param buffer
    * @param levels
    * @param maxItemCount
    * @param maxItemWidth
    * @param padding
    */
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

  /**
    * This function takes an array of array with numbers.
    * Each array row contains the value of a node. The
    * children of the node anre in the next line. They are assumed
    * to be two children. So each line below should contain twice as many.
    * The last line can contain less. The array of nodes is build from
    * the bottom up.
    *
    * @param data
    * @return
    */
  def makeTree(data: Array[Array[Int]]): Node = {
    val nodeArray: Array[Array[Node]] = Array.ofDim(data.length)
    //reverse the array so we can build from the bottom up.
    val reversed = data.reverse
    for {
      i <- reversed.indices
      j <- reversed(i).indices
    } yield {
      //First time the inner array is accessed it will be empty.
      if (nodeArray(i) == null)
        nodeArray(i) = Array.ofDim(reversed(i).length)
      //Take the integer value and then the next 2 Nodes previously
      //build are assigned to the left and right.
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
    //Return the last node in the array of array. That would be the top root node.
    nodeArray(nodeArray.length - 1)(nodeArray(nodeArray.length - 1).length - 1)
  }

  /**
    * This function converts an single linear array. Into an array of arrays
    * where each row array contains the values of a level in a binary tree.
    *
    * @param array
    * @return
    */
  def arrayToTree(array: Array[Int]): ArrayBuffer[Array[Int]] = {
    var levels = 0  //Number of levels in tree
    var totalItems = 0
    var itemsLastLevel = 1 //number of entries in the last level
    val buffer: ArrayBuffer[Array[Int]] = ArrayBuffer()
    while (totalItems < array.length) {
      //buffer(levels) = Array.ofDim(itemsLastLevel)
      //add to the buffer the count of items from previous level
      buffer += array.slice(totalItems, totalItems + itemsLastLevel)
      totalItems = totalItems + itemsLastLevel
      if (totalItems < array.length) {
        //Each level should have twice as much the previous level
        itemsLastLevel = itemsLastLevel * 2
        levels = levels + 1
      }
    }
    //The maximum items in any level. It is the last one or before the last one.
    val maxItemCount = max(buffer(levels).length, max(0, buffer(levels - 1).length))
    //The size of the largest integer as string.
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
