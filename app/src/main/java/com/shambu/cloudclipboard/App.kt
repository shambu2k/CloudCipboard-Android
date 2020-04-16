package com.shambu.cloudclipboard

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import com.shambu.cloudclipboard.utils.Constants.Companion.FOREGROUND_SERVICE_CHANNEL_ID

class App: Application() {

    override fun onCreate() {
        super.onCreate()

        createNotificationChannel()
    }

    private fun createNotificationChannel() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val foregroundServiceChannel = NotificationChannel(
                FOREGROUND_SERVICE_CHANNEL_ID, "Clipboard Listener", NotificationManager.IMPORTANCE_DEFAULT
            )

            val manager: NotificationManager = getSystemService(NotificationManager::class.java) as NotificationManager

            manager.createNotificationChannel(foregroundServiceChannel)
        }
    }
}