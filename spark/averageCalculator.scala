import org.apache.spark.{SparkConf, SparkContext}
object AverageCalculator {
def main(args: Array[String]): Unit = {
// Step 1: Set up the Spark context
val conf = new SparkConf().setAppName("AverageCalculator").setMaster("local")
val sc = new SparkContext(conf)
// Path to the input CSV file
val inputFile = "path/to/your/input.csv" // Replace with the actual path to your CSV file
// Step 2: Read the CSV file into an RDD
val lines = sc.textFile(inputFile)
// Step 3: Parse the RDD to extract integer values
val numbers = lines.map(line => line.toInt)
// Step 4: Use RDD operations to compute the sum and count of the integers
val sum = numbers.reduce(_ + _)
val count = numbers.count()
// Step 5: Calculate the average from the sum and count
val average = sum.toDouble / count
// Print the average
println(s"The average of the numbers is: $average")
// Stop the Spark context
sc.stop()
}
}
