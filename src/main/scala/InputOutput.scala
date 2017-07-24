import java.io.File

/**
  * Created by knoldus on 7/24/17.
  */
trait InputOutput {
  def read(f:File): String

  def write(file: File, content: String, dirPath: String): Boolean
}
