package com.candroid.realtracker.brodcastreciver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import com.candroid.realtracker.habittracker.util.Constants
import com.shivam.habittrakker.alarmservices.AlarmServices
import io.karn.notify.Notify
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

class AlarmReceiver:BroadcastReceiver() {
    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun onReceive(context: Context?, intent: Intent?) {
        val timeInMillis=intent?.getLongExtra(Constants.EXTRA_EXACT_ALARM_TIME,0)
        when(intent?.action){
            Constants.ACTION_SET_EXACT->
            {
                if (context != null) {
                    timeInMillis?.let { convertDate(it) }?.let {
                        buildNotification(context, "Set Exact Time",
                            it
                        )
                    }
                }


            }
            Constants.ACTION_SET_REPETITIVE_EXACT -> {
                context?.let { AlarmServices(it) }?.let { setRepetitiveAlarm(it) }
                if (context != null) {
                    timeInMillis?.let { convertDate(it) }?.let {
                        buildNotification(context, "Set Repetitive Exact Time",
                            it
                        )
                    }
                }
            }

        }

    }


    fun buildNotification(context: Context,title:String,message:String){
        Notify.with(context).content {
            this.title = title
            text = "I got triggered at - $message"
        }
            .show()

    }
    @RequiresApi(Build.VERSION_CODES.KITKAT)
    private fun setRepetitiveAlarm(alarmService: AlarmServices) {
        val cal = Calendar.getInstance().apply {
            this.timeInMillis = timeInMillis + TimeUnit.DAYS.toMillis(1)
            Timber.d("Set alarm for next week same time - ${convertDate(this.timeInMillis)}")
        }
        alarmService.setRepetitiveAlarm(cal.timeInMillis)
    }
    private fun convertDate(timeInMillis: Long): String {
        val formatter = SimpleDateFormat("dd/MM/yyyy hh:mm:ss")
        return formatter.format(Date(timeInMillis)).toString()

    }



}