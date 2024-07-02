object capitalize{
def capitalizeWords(sentence : String) : String = {
sentence.split(" ").map(word => word.capitalize).mkString(" ")
}

def main(args : Array[String]) : Unit = {
val sentence = "hello how are you? this is scala."
val capitals = capitalizeWords(sentence)
println(capitals)
}
}
