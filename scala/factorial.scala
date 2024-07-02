object factorial{
def factorials(n : Int) : Int = {
if(n==0 || n==1) 1
else n * factorials(n-1)
}
def main(args : Array[String]) : Unit = {
var arr= Array(1,2,3,5);
arr.foreach { x=>println(factorials(x))
}
}
}
