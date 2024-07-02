class funquick {
def quickSort(arr : List[Int]) : List[Int] = arr match {
case Nil => Nil
case pivot :: tail =>
val(left , right) = tail.partition(_ < pivot)
quickSort(left) ::: pivot :: quickSort(right)
}
def main(args : Array[String]) : Unit = {
val arr=List(5,3,16,12,10,8)
println("unsorted array"+arr.mkString(", "))
val x=quickSort(arr)
println("sorted array"+x.mkString(", "))
}
}
