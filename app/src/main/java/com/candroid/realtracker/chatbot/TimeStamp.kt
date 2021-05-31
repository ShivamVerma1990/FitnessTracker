package com.candroid.realtracker.chatbot

import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.*

object TimeStamp {
fun time():String {
    val timeStamp = Timestamp(System.currentTimeMillis())
    val sdf = SimpleDateFormat("HH::mm")
    val time = sdf.format(Date(timeStamp.time))
return time
}

}