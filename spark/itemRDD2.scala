import org.apache.spark.{SparkConf, SparkContext}
object ItemRDD {
def main(args: Array[String]): Unit = {
// Step 1: Set up the Spark context
val conf = new SparkConf().setAppName("ItemRDD").setMaster("local")
val sc = new SparkContext(conf)
// Step 2: Create the Item map
val itemMap = Map("Ball" -> 10, "Ribbon" -> 50, "Box" -> 20, "Pen" -> 5, "Book" -> 8, "Dairy" -> 4,
"Pin" -> 20)
// Step 3: Parallelize the Item map into an RDD with 3 partitions
val rdd = sc.parallelize(itemMap.toSeq, 3)
// Step 4: Find number of partitions created
val numPartitions = rdd.getNumPartitions
println(s"Number of partitions created: $numPartitions")
// Step 5: Display content of the RDD
println("Content of the RDD:")
rdd.collect().foreach(item => println(s" $item"))
// Step 6: Display content of each partition separately
println("\nContent of each partition separately:")
val partitionedRDD = rdd.mapPartitionsWithIndex { (index, iterator) =>
Iterator((index, iterator.toList))
}
partitionedRDD.collect().foreach { case (index, partitionContents) =>
println(s"Partition $index:")
partitionContents.foreach(item => println(s" $item"))
}
// Step 7: Stop the Spark context
sc.stop()
}
}
