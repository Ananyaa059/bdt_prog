import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.streaming.dstream.DStream
object SocketWordCount {
def main(args: Array[String]): Unit = {
// Step 1: Create a local StreamingContext with two working threads and batch interval of 5
seconds
val conf = new SparkConf().setAppName("SocketWordCount").setMaster("local[2]")
val ssc = new StreamingContext(conf, Seconds(5))
// Step 2: Create a DStream that will connect to hostname:port, like localhost:9999
val lines = ssc.socketTextStream("localhost", 9999)
// Step 3: Split each line into words
val words: DStream[String] = lines.flatMap(_.split("\\W+"))
// Step 4: Count each word in each batch
val pairs: DStream[(String, Int)] = words.map(word => (word, 1))
val wordCounts: DStream[(String, Int)] = pairs.reduceByKey(_ + _)
// Step 5: Print the word counts
wordCounts.print()
// Step 6: Start the computation
ssc.start()
// Step 7: Wait for the computation to terminate
ssc.awaitTermination()
}
}
