package demo

/**
  * Created by db2admin on 6/10/2016.
  */
object Test1 {

  def countLines(file: String): Int ={
    val lines = scala.io.Source.fromFile(file).getLines().toList
    lines.foldLeft(0)(_+Integer.parseInt(_))
  }
}
