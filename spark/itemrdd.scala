import org.apache.spark.{SparkConf, SparkContext}
object ItemRDD {
def main(args: Array[String]): Unit = {
// Step 1: Set up the Spark context
val conf = new SparkConf().setAppName("ItemRDD").setMaster("local")
val sc = new SparkContext(conf)
// Step 2: Create the Item map
val itemMap = Map("Ball" -> 10, "Ribbon" -> 50, "Box" -> 20, "Pen" -> 5, "Book" -> 8, "Dairy" -> 4,
"Pin" -> 20)
// Step 3: Create an RDD from the Item map
val rdd = sc.parallelize(itemMap.toSeq)
// Step 4: Find number of partitions created
val numPartitions = rdd.getNumPartitions
println(s"Number of partitions created: $numPartitions")
// Step 5: Display content of each partition separately
val partitionedRDD = rdd.mapPartitionsWithIndex { (index, iterator) =>
Iterator((index, iterator.toList))
}
// Collect and print the results
val results = partitionedRDD.collect()
results.foreach { case (index, partitionContents) =>
println(s"Partition $index:")
partitionContents.foreach(item => println(s" $item"))
}
// Step 6: Stop the Spark context
sc.stop()
}
}
