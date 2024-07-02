import org.apache.spark.{SparkConf, SparkContext}
object CombineByKeyExample {
def main(args: Array[String]): Unit = {
// Step 1: Set up the Spark context
val conf = new SparkConf().setAppName("CombineByKeyExample").setMaster("local")
val sc = new SparkContext(conf)
// Step 2: Create the RDD with the given collection
val data = Seq(("coffee", 2), ("cappuccino", 5), ("tea", 3), ("coffee", 10), ("cappuccino", 15))
val rdd = sc.parallelize(data)
// Step 3: Apply combineByKey to combine values by key
val combinedRDD = rdd.combineByKey(
(value: Int) => value, // createCombiner: initialize the accumulator for each key
(acc: Int, value: Int) => acc + value, // mergeValue: add an element to the accumulator
(acc1: Int, acc2: Int) => acc1 + acc2 // mergeCombiners: combine accumulators from different
partitions
)
// Step 4: Print the combined results
val results = combinedRDD.collect()
results.foreach { case (key, sum) =>
println(s"$key: $sum")
}
// Step 5: Stop the Spark context
sc.stop()
}
}
