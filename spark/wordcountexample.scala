import org.apache.spark.{SparkConf, SparkContext}
object WordCountExample {
def main(args: Array[String]): Unit = {
// Step 1: Set up the Spark context
val conf = new SparkConf().setAppName("WordCountExample").setMaster("local")
val sc = new SparkContext(conf)
// Step 2: Read the text file and split into words
val inputFile = "path/to/words.txt" // Replace with the actual path to your words.txt file
val lines = sc.textFile(inputFile)
val words = lines.flatMap(line => line.split("\\s+"))
// Step 3: Count the occurrences of each word
val wordCounts = words.map(word => (word.toLowerCase, 1))
.reduceByKey(_ + _)
// Step 4: Arrange word counts in ascending order based on key
val sortedWordCounts = wordCounts.sortByKey()
// Step 5: Display words that begin with 's'
val wordsStartingWithS = sortedWordCounts.filter { case (word, count) => word.startsWith("s") }
.collect()
// Step 6: Print the results
println("Word counts:")
wordCounts.collect().foreach { case (word, count) =>
println(s"$word: $count")
}
println("\nWord counts sorted in ascending order based on key:")
sortedWordCounts.collect().foreach { case (word, count) =>
println(s"$word: $count")
}
println("\nWords that begin with 's':")
wordsStartingWithS.foreach { case (word, count) =>
println(s"$word: $count")
}
// Step 7: Stop the Spark context
sc.stop()
}
}
