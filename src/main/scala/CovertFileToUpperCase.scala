import java.io.File

/**
  * Created by knoldus on 7/24/17.
  */
class CovertFileToUpperCase extends InputOutputFromFolder{

  def upperCase(inputFilePath: String, outputFilePath: String, extension: String): Boolean = {

    val dirPath = new File(inputFilePath)
    val allFileList = getAllFiles(dirPath)
    val fileList = extension match {
      case "all" => allFileList

      case _ => {
        val fileListCheck = allFileList.filter(f => f.getName.endsWith(extension)).toList

        if(fileListCheck.isEmpty)
        {
          throw new Exception("Either invalid extension or file with such extension doesn't exist in the given path")
        }
        else
        {
          fileListCheck
        }
      }
    }

    val fileContentList = fileList.map(read(_)).map(_.toUpperCase())

    val fileContentTupleList = fileList.zip(fileContentList)

    val checkResult = fileContentTupleList.map(t=>write(t._1,t._2,outputFilePath))

    if(checkResult.contains(false))
    {
      false
    }
    else
    {
      true
    }
  }

  private def getAllFiles(dir: File): List[File] = {

    val listOfFiles = dir.listFiles().toList

    listOfFiles.filter(_.isFile) ::: listOfFiles.filter(_.isDirectory).flatMap(x => getAllFiles(x))

  }
}

