import scala.io.StdIn.readLine
object highestWordLength {
def findLongestWord(words: List[String]): (String, Int) = {
words.map(word => (word, word.length)).maxBy(_._2)
}
def main(args: Array[String]): Unit = {
println(s"Enter words separated by commas (e.g.,
games,television,rope,table):")
val input = readLine()
val words = input.split(",").map(_.trim).toList
val (longestWord, length) = findLongestWord(words)
println(s"The longest word is '$longestWord' with length
$length.")
 }
}
