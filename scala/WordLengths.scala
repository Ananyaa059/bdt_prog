object WordLengths {
def main(args : Array[String]) : Unit = {
val words = Array("apple" , "banana", "fig" , "orange")
val wordLength = words.map(word => (word , word.length))

println("word length")

wordLength.foreach { case(word , length) => println(s"$word : $length") }
}
}
