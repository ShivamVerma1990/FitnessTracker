package com.candroid.realtracker.stepCounter

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.ContextWrapper
import android.os.Build

class App : android.app.Application() {

    override fun onCreate() {
        super.onCreate()

        PrefsHelper.Builder()
            .setContext(this)
            .setMode(ContextWrapper.MODE_PRIVATE)
            .setPrefsName("mPrefs")
            .setUseDefaultSharedPreference(true)
            .build();

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