import org.apache.spark.sql.SparkSession
object WordCount {
 def main(args: Array[String]): Unit = {
 val spark = SparkSession.builder()
 .appName("Word Count")
 .master("local[*]")
 .getOrCreate()

 val sc = spark.sparkContext
 // Read the text file into an RDD
 val inputFile = "path/to/text.txt"
 val textFile = sc.textFile(inputFile)
 // Perform word count using pair RDDs
 val wordCounts = textFile
 .flatMap(line => line.split("\\s+")) // Split each line into words
 .filter(word => word.nonEmpty) // Filter out empty words
 .map(word => (word, 1)) // Map each word to a pair (word, 1)
 .reduceByKey(_ + _) // Reduce by key to count occurrences
 // Collect and print the results
 wordCounts.collect().foreach { case (word, count) =>
 println(s"$word: $count")
 }
 spark.stop()
 }
}
