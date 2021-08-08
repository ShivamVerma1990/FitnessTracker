package com.candroid.realtracker.chatbot

import com.candroid.realtracker.chatbot.Constant.OPEN_GOOGLE
import com.candroid.realtracker.chatbot.Constant.OPEN_SEARCH

object BotResponse {
fun response(_message:String):String{
    var random=(0..2).random()
    val message=_message.toLowerCase()
return when{
    message.contains("hello")->{

    "hi sir please tell me about your level sir \n beginner ?\n  medium? \n  advance?"

    }
message.contains("how are you")->
{
    when(random){
        0->"i am good"
        1->"i am good sir and you"
2->"pretty good! How about you?"
    else->"error"

    }

}
    message.contains("beginner")->
    {
        "so sir your level is beginner \n so sir i have a best diet  for you to gain weight do you wanna any diet plan or not \n yes or no"


    }
    message.contains("i am also good")->
    {
"oh nice sir!"

    }

    message.contains("medium")->
    {
        "so sir your level is medium \n so sir i have a best diet  for you to gain weight do you wanna any diet plan or not \n yes or no"

    }
    message.contains("advance")->
    {


            "so sir your level is advance \n so sir i have a best diet  for you to gain weight do you wanna any diet plan or not \n yes or no"





    }
    message.contains("yes")->
    {

"1. Homemade protein smoothies :\n There are many delicious protein smoothie recipes. Avoid most commercial versions, which may contain added sugar and aren’t as nutritious.\n 2. Milk: \n Milk has been used as a weight gainer or muscle builder for decades (1)\n Drinking milk is a great way to add protein to your diet. It contains both casein and whey proteins.\n3. Rice\nRice is a great source of carbs that are easy to consume and digest.\n" +
        "\n4.Nuts and nut butters: \nNuts and nut butters are delicious, high calorie treats. They’re great for you and easy to add to many different snacks or recipes\n5. Red meats:\nRed meat is an excellent source of protein that will help you gain muscle. It contains leucine, an amino acid that helps stimulate muscle protein synthesis. The fattier the meat, the more calories you’ll take in.\n6. Potatoes and starches:\nHealthy starches are a great way to get important nutrients and fiber, boost your calorie intake, and increase your muscle glycogen stores.\n8. Protein supplements:\nProtein supplements are a simple and affordable addition to your diet to help you increase your protein intake.\n And repeat same process every day monday to sunday"





    }

//Open Google
    message.contains("open") && message.contains("google")-> {
        OPEN_GOOGLE
    }

    //Search on the internet
    message.contains("search")-> {
        OPEN_SEARCH
    }
    message.contains("What kind of Veggies have the most protein?")-> {
      "Vegetables high in protein include lima beans, bean sprouts, green peas, spinach, sweet corn, asparagus, artichokes, brussels sprouts, mushrooms, and broccoli. For more vegetarian and vegan sources of protein see the articles on beans and legumes highest in protein, and grains high in protein, and high protein nuts."
    }
    message.contains("ok")-> {
        "if you have any queries then you can ask sir we are here try to solve your every problem"
    }
message.contains("time")&&message.contains("?")->{
    TimeStamp.time()
}
    message.contains("no")->
    {

            "ok sir as your wise welcome to our fitness app if you have any problem so just tell us \n hope our team try to solve your problem as soon as possible"



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



