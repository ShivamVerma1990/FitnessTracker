package com.candroid.realtracker.chatbot

object BotResponse {
fun response(_message:String):String{
    var random=(0..2).random()
    val message=_message.toLowerCase()
return when{
    message.contains("hello")->{
when(random){
    0->"hi"
    1->"yo"
2->"sup"
    else->
        "error"
}
    }
message.contains("How are you")->
{
    when(random){
        0->"i am good"
        1->"i am busy right now"
2->"Pretty good! How about you?"
    else->"error"

    }

}
message.contains("time")&&message.contains("?")->{
    TimeStamp.time()
}
else->{
    when(random){
        0 -> "I don't understand..."
        1 -> "Try asking me something different"
        2 -> "Idk"
        else -> "error"

    }

}
}
}
    }



