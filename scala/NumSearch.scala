object NumSearch {
def Search(numbers : List[Int] , target : Int) : Boolean = {
numbers.contains(target)
}
def main(args : Array[String]) : Unit = {
val numbers = List(9,2,3,4,12,134,98)
val target1=12
val target2=90
println(s"Is $target1 present? : ${Search(numbers,target1)}")
println(s"Is $target2 present? : ${Search(numbers,target2)}")
}
}
