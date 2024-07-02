import org.apache.spark.{SparkConf, SparkContext}
object WordCount {
def main(args: Array[String]): Unit = {
// Step 1: Set up the Spark context
val conf = new SparkConf().setAppName("WordCount").setMaster("local")
val sc = new SparkContext(conf)
// Step 2: Read the text file into an RDD
val inputFile = "path/to/text.txt" // Replace with the path to your text file
val lines = sc.textFile(inputFile)
// Step 3: Perform the word count
val words = lines.flatMap(line => line.split("\\W+")) // Split on non-word characters
val wordCounts = words.map(word => (word.toLowerCase, 1)).reduceByKey(_ + _)
// Step 4: Filter words that appear more than 4 times
val frequentWords = wordCounts.filter { case (word, count) => count > 4 }
// Step 5: Save the result to a file
val outputFile = "path/to/output" // Replace with the desired output directory
frequentWords.saveAsTextFile(outputFile)
// Step 6: Collect and display the frequent words
val result = frequentWords.collect()
println("Words that appear more than 4 times:")
result.foreach { case (word, count) => println(s"$word: $count") }
// Stop the Spark context
sc.stop()
}
}
