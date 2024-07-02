import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._
object TopTweeters {
def main(args: Array[String]): Unit = {
// Step 1: Set up the Spark context and Spark session
val conf = new SparkConf().setAppName("TopTweeters").setMaster("local")
val sc = new SparkContext(conf)
val spark = SparkSession.builder().config(conf).getOrCreate()
// Step 2: Read the JSON file into a DataFrame
val inputFile = "path/to/reduced-tweets.json" // Replace with the path to your JSON file
val tweetsDF = spark.read.json(inputFile)
// Step 3: Create an RDD from the user column
val usersRDD = tweetsDF.select("user").rdd.map(row => row.getString(0))
// Step 4: Perform the word count (user count) using pair RDD operations
val userPairs = usersRDD.map(user => (user, 1))
val userCounts = userPairs.reduceByKey(_ + _)
// Step 5: Get the top 10 tweeters
val topTweeters = userCounts.sortBy(_._2, ascending = false).take(10)
// Step 6: Print the top 10 tweeters
println("Top 10 Tweeters:")
topTweeters.foreach { case (user, count) => println(s"$user: $count") }
// Stop the Spark context
sc.stop()
spark.stop()
}
}
