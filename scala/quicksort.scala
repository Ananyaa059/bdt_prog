object quicksort{
def quickSort(arr : Array[Int]) : Unit = {

def swap(i : Int, j: Int) : Unit = {
val temp=arr(i)
arr(i)=arr(j)
arr(j)=temp
}

def part(low : Int , high : Int) : Int = {
val p=arr(high)
var i=low-1
for(j <- low until high) {
if(arr(j) <= p) {
i += 1
swap(i,j)
}
}
swap(i+1,high)
i+1
}

def quickSortRec(low : Int , high : Int) : Unit = {
if(low < high){
val p = part(low,high)
quickSortRec(low,p-1)
quickSortRec(p+1,high)
}
}
quickSortRec(0 , arr.length - 1)
}
def main(args : Array[String]) : Unit = {
val arr=Array(12,8,3,2,0,5,14)
println("Array unsorted:"+arr.mkString(", "))
quickSort(arr)
println("Array sorted: "+arr.mkString(", "))
}
}


