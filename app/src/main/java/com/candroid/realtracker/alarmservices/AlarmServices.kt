package com.shivam.habittrakker.alarmservices

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import com.candroid.realtracker.brodcastreciver.AlarmReceiver
import com.candroid.realtracker.habittracker.util.Constants
import com.candroid.realtracker.habittracker.util.RandomUtil


class AlarmServices(val context: Context) {
    val alarmManager: AlarmManager? =
        context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    fun setExactAlarm(timeInMills: Long) {
        setAlarm(timeInMills, getPendingIntent(getIntent().apply {
            action = Constants.ACTION_SET_EXACT
            putExtra(Constants.EXTRA_EXACT_ALARM_TIME, timeInMills)
        }))


    }

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    fun setRepetitiveAlarm(timeInMills: Long) {
        setAlarm(
            timeInMills,
            getPendingIntent(
                getIntent().apply {
                    action = Constants.ACTION_SET_REPETITIVE_EXACT
                    putExtra(Constants.EXTRA_EXACT_ALARM_TIME, timeInMills)
                }
            )
        )


    }

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    fun setAlarm(timeInMills: Long, pendingIntent: PendingIntent) {
        alarmManager?.let {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                alarmManager.setExactAndAllowWhileIdle(
                    AlarmManager.RTC_WAKEUP,
                    timeInMills,
                    pendingIntent
                )


            } else {
                alarmManager.setExact(
                    AlarmManager.RTC_WAKEUP,
                    timeInMills,
                    pendingIntent
                )

            }

        }


    }

    private fun getRandomRequestCode() = RandomUtil.getRandomInt()
    fun getIntent(): Intent = Intent(context, AlarmReceiver::class.java)
    fun getPendingIntent(intent: Intent): PendingIntent = PendingIntent.getBroadcast(
        context,
        getRandomRequestCode(),
        intent,
        PendingIntent.FLAG_UPDATE_CURRENT
    )

fun cancelAlarm(){
    val myIntent = Intent(
        context.applicationContext,
        AlarmReceiver::class.java
    )
    val pendingIntent = PendingIntent.getBroadcast(
        context.applicationContext, 1, myIntent, 0
    )
    alarmManager?.cancel(pendingIntent);
}

}