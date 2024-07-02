object BookCount{
def main(args : Array[String]) : Unit = {
val books = List(("Dr. Seuss", "How the Grinch Stole Christmas!") ,
("Dr. Seuss", "How the Grinch Stole Christmas!") ,
("Dr. John", "How the Grinch Stole Christmas!") ,
("Dr. Seuss", "How the Grinch Stole Christmas!") ,
("Dr. John", "How the Grinch Stole Christmas!") ,
("Dr. Patry", "How the Grinch Stole Christmas!") )

val bookCount = authorBooks(books)

bookCount.foreach { case(author,count) =>
println(s"$author : $count books")
}
}
def authorBooks(books : List[(String , String)]) : Map[String , Int] = {
books.groupBy(_._1).mapValues(_.size)
}
}
