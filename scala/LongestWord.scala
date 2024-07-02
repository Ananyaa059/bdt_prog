import scala.io.StdIn.readLine
object LongestWord{
def findLong(words : List[String]) : (String,Int) = {
words.map(word=>(word,word.length)).maxBy(_._2)
}
def main(args: Array[String]) : Unit = {
println("enter words seperated by comma (eg: rat, cat)")
val input = readLine()
val words = input.split(",").map(_.trim).toList
val (longestWord, length) = findLong(words)
println(s"The longest word is $longestWord with length $length")
}
}
