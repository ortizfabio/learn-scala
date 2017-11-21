package com.bytetrend.sandbox.scala.challenge

import scala.collection.immutable.TreeMap
import scala.collection.mutable.Map
import scala.io.{BufferedSource, Source}
import scala.util.matching.Regex

case class Stats(count: Int, paragraph: List[Int]) {
  override def toString: String = {
    s"{$count:${paragraph.mkString(",")}}"
  }
}

/**
  * <Bridewater>
  * Encapsulates reading a file that is located in the classpath of this project.
  */
trait BufferedFileSource {
  /**
    * Reads a file and returns the content as a BufferedSource.
    *
    * @param filename
    * @return
    */
  def bufferedFile(filename: String): BufferedSource = {
    val url = this.getClass.getClassLoader.getResource(filename).toURI.toURL
    Source.fromURL(url)
  }
}

trait PrettyPrint {
  /**
    * Prints the keys and values of a map in two columns separated by at most the given number of spaces.
    * The amount  of spaces depends on the size of the key.
    *
    * @param map    a map of key and values.
    * @param spaces maximum spaces between the key and value.
    */
  def prettyPrint[T](map: Map[T, _], spaces: Int)(implicit ordering: Ordering[T]) = {
    TreeMap(map.toArray: _*).toList.foreach((x => println(s"${x._1}${' '.toString * (spaces - x._1.toString.length)}${x._2}")))
  }
}

object Concordance extends App with BufferedFileSource with PrettyPrint {
  /** Regex expression to parse text into paragraphs */
  val reg: Regex = "(\\.)(\\s*)([A-Z\n\r]|$)".r
  /** parses the text into a sequence of words and dots only */
  val words: Iterator[String] = bufferedFile("concordance.txt").getLines.flatMap(x => x.replaceAll(",", "").split("\\s"))
  /** breaks the sequence into paragraphs by splitting it at each dot that ends a paragraphs
    * it assumes that the next character after the dot is a capitalized letter or a new line.
    */
  val paragraphs: Array[String] = reg.replaceAllIn(words.mkString(" "), "\n$3").split("\n")
  /**
    * This function takes a word and a map of words and Stats. It adds or updates the stats of the word.
    * It is called from a foldLeft to process a sequence of words.
    */
  val processor = (mapAndPrgrhCount: (Map[String, Stats], Int), textLine: String) => {
    val (map, paragraphIndex) = mapAndPrgrhCount
    textLine.split("\\s").map(word => {
      val stat = map.getOrElseUpdate(word, Stats(0, List()))
      map(word) = Stats((stat.count + 1), stat.paragraph :+ paragraphIndex)
    })
    (map, paragraphIndex + 1)
  }

  /** concordance work is done here. Once that text has been parsed into paragraphs. Main goal is to make this code very readable. */
  val (mapResult, _) = paragraphs.foldLeft((Map[String, Stats](), 1))(processor)

  prettyPrint(mapResult, 40)
}
