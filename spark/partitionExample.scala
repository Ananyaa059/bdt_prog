import org.apache.spark.{SparkConf, SparkContext}
object PartitionExample {
def main(args: Array[String]): Unit = {
// Step 1: Set up the Spark context
val conf = new SparkConf().setAppName("PartitionExample").setMaster("local")
val sc = new SparkContext(conf)
// Step 2: Create a collection
val data = Array(11, 34, 45, 67, 3, 4, 90)
// Step 3: Create an RDD from the collection with 3 partitions
val rdd = sc.parallelize(data, 3)
// Step 4: Use mapPartitionsWithIndex to return content of each partition along with partition index
// and increment each element by 1
val partitionedRDD = rdd.mapPartitionsWithIndex((index, iterator) => {
iterator.map(element => (index, element + 1))
})
// Step 5: Collect and print the results
val results = partitionedRDD.collect()
results.foreach { case (index, value) => println(s"Partition: $index, Value: $value") }
// Stop the Spark context
sc.stop()
}
}
