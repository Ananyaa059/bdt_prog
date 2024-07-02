import org.apache.spark.{SparkConf, SparkContext}
object AverageMarks {
def main(args: Array[String]): Unit = {
// Step 1: Set up the Spark context
val conf = new SparkConf().setAppName("AverageMarks").setMaster("local")
val sc = new SparkContext(conf)
// Sample Input Data
val data = Array(
("Joe", "Maths", 83), ("Joe", "Physics", 74), ("Joe", "Chemistry", 91), ("Joe", "Biology", 82),
("Nik", "Maths", 69), ("Nik", "Physics", 62), ("Nik", "Chemistry", 97), ("Nik", "Biology", 80)
)
// Step 2: Create an RDD from the sample data
val rdd = sc.parallelize(data)
// Step 3: Create a pair RDD with (student, marks)
val studentMarks = rdd.map { case (student, subject, marks) => (student, marks) }
// Step 4: Use combineByKey to compute sum of marks and count of subjects for each student
val combineByKeyResult = studentMarks.combineByKey(
(marks: Int) => (marks, 1), // CreateCombiner: initial (sum, count)
(acc: (Int, Int), marks: Int) => (acc._1 + marks, acc._2 + 1), // MergeValue: update (sum, count)
(acc1: (Int, Int), acc2: (Int, Int)) => (acc1._1 + acc2._1, acc1._2 + acc2._2) // MergeCombiners:
combine (sum, count)
)
// Step 5: Calculate the average marks for each student
val averageMarks = combineByKeyResult.map { case (student, (sum, count)) => (student,
sum.toDouble / count) }
// Step 6: Collect and print the results
val result = averageMarks.collect()
println("Average marks for each student:")
result.foreach { case (student, avgMarks) => println(s"$student: $avgMarks") }
// Stop the Spark context
sc.stop()
}
}
