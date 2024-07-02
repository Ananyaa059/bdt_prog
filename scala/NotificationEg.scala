abstract class Notification

case class Email(sender : String , title : String , body : String) extends Notification 
case class SMS(caller : String , message : String) extends Notification 

object NotificationEg {
def showNoti(notification : Notification) : String = {
notification match {
case Email(sender , title , _) =>
s"You have got an email from $sender and title $title"
case SMS(caller , message) =>
s"You have got an sms from $caller and message is : $message"
}
}

def main(args : Array[String]) : Unit = {
val email = Email("abc@gmail.com" , "HI" , "hi how are you")
val sms = SMS("1234567890" , "your package is here")

println(showNoti(email))
println(showNoti(sms))
}
}
