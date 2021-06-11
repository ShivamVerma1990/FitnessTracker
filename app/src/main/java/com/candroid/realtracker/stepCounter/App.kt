package com.candroid.realtracker.stepCounter

import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build

class App : android.app.Application() {

    override fun onCreate() {
        super.onCreate()


        createNotificationChannel()
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val serviceChannel = NotificationChannel(
                "CHANNEL_ID",
                "Contact Tracing Service",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            val manager = getSystemService(
                NotificationManager::class.java
            )
            manager.createNotificationChannel(serviceChannel)
        }
    }
}