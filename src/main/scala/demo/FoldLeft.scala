package demo


object FoldLeft {

  def countLines(file: String): Int ={
    val lines = scala.io.Source.fromFile(file).getLines().toList
    lines.foldLeft(0)(_+Integer.parseInt(_))
  }
}
