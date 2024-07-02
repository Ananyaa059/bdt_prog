import org.apache.spark.{SparkConf, SparkContext, Partitioner}
import org.apache.spark.rdd.RDD
object EmployeePartitionerExample {
case class Employee(EmpID: String, Dept: String, EmpDesg: String)
def main(args: Array[String]): Unit = {
// Step 1: Set up the Spark context
val conf = new SparkConf().setAppName("EmployeePartitioner").setMaster("local")
val sc = new SparkContext(conf)
// Sample Input Data
val employeeData = Array(
Employee("1", "HR", "Manager"),
Employee("2", "Finance", "Analyst"),
Employee("3", "IT", "Developer"),
Employee("4", "IT", "Manager"),
Employee("5", "HR", "Executive"),
Employee("6", "Finance", "Manager"),
Employee("7", "IT", "Analyst"),
Employee("8", "HR", "Developer")
)
// Step 2: Create an RDD from the sample data
val employeeRDD: RDD[Employee] = sc.parallelize(employeeData)
// Step 3: Map the RDD to pair RDD with Dept as the key
val pairRDD = employeeRDD.map(emp => (emp.Dept, emp))
// Step 4: Define a custom partitioner
class DeptPartitioner(partitions: Int) extends Partitioner {
require(partitions > 0, s"Number of partitions ($partitions) must be positive.")
override def numPartitions: Int = partitions
override def getPartition(key: Any): Int = {
key.hashCode % numPartitions
}
}
// Step 5: Partition the RDD using the custom partitioner
val partitionedRDD = pairRDD.partitionBy(new DeptPartitioner(4))
// Step 6: Save the partitioned RDD to files (optional)
val outputDir = "path/to/output" // Replace with the desired output directory
partitionedRDD.saveAsTextFile(outputDir)
// Step 7: Verify the partitions (for demonstration purposes)
partitionedRDD.mapPartitionsWithIndex((index, iterator) => iterator.map((index, _)))
.collect()
.foreach { case (index, (dept, emp)) =>
println(s"Partition: $index, Dept: $dept, Employee: $emp")
}
// Stop the Spark context
sc.stop()
}
}
