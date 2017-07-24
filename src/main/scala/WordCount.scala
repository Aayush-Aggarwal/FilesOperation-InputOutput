import java.io.File
import java.io.{File, PrintWriter}


/**
  * Created by knoldus on 7/24/17.
  */
class WordCount extends InputOutputFromFolder {

  def countUniqueWordsFromFile(inputPath: String, outputPath: String): Boolean = {

    val fileList = new File(inputPath).listFiles().toList

    val checkResult = fileList.map(countUniqueWordsFile(_, outputPath))

    if(checkResult.contains(false))
    {
      false
    }
    else
    {
      true
    }

  }

  /*
  // Another way to Implement Word count using foldLeft..

val FileMap=fromFile(inputPath).getLines.mkString("\n").
toLowerCase.split(" ").foldLeft(Map.empty[String, Int]) {
(count, word) => count + (word -> (count.getOrElse(word, 0) + 1))

val regex ="""[A-Za-z]+""".r

val words = for {
  s <- regex.findAllIn(FileMap)
}yield s


val s=FileMap.mkString("\n")

val nw=new PrintWriter(outputPath){
  write(s)
  close()
}*/
  private def countUniqueWordsFile(file: File, outputPath: String): Boolean = {

    val fileContent = read(file).toLowerCase

    val regex = """[A-Za-z]+""".r

    val words = for {
      s <- regex.findAllIn(fileContent)
    } yield s

    val listOfWords = words.toList
    val mapOfWordCount = listOfWords.groupBy(identity).mapValues(_.size)

    write(file, mapOfWordCount.mkString("\n"), outputPath)
  }
}

