package com.shambu.cloudclipboard

import android.app.Notification
import android.app.PendingIntent
import android.app.Service
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.shambu.cloudclipboard.model.ClipboardData
import com.shambu.cloudclipboard.model.ClipboardRepository
import com.shambu.cloudclipboard.utils.Constants.Companion.FOREGROUND_NOTIFICATION_ID
import com.shambu.cloudclipboard.utils.Constants.Companion.FOREGROUND_SERVICE_CHANNEL_ID
import com.shambu.cloudclipboard.utils.Constants.Companion.FOREGROUND_SERVICE_PENDINGINTENT_FLAGS
import com.shambu.cloudclipboard.utils.Constants.Companion.FOREGROUND_SERVICE_PENDINGINTENT_RC
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import java.util.*

class ClipboardService: Service() {

    private lateinit var notification: Notification
    private lateinit var notificationIntent: Intent
    private lateinit var pendingIntent: PendingIntent
    private lateinit var clipboardManager: ClipboardManager
    private lateinit var clipboardRepository: ClipboardRepository
    private val serviceScope: CoroutineScope = CoroutineScope(Dispatchers.IO)

    override fun onCreate() {
        super.onCreate()
        notificationIntent = Intent(this, MainActivity::class.java)
        pendingIntent = PendingIntent.getActivity(this,
            FOREGROUND_SERVICE_PENDINGINTENT_RC, notificationIntent, FOREGROUND_SERVICE_PENDINGINTENT_FLAGS)

        notification = NotificationCompat.Builder(this, FOREGROUND_SERVICE_CHANNEL_ID)
            .setContentTitle(getString(R.string.notif_foreground_service_title))
            .setContentText(getString(R.string.notif_foreground_service_content))
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentIntent(pendingIntent)
            .build()

        clipboardManager = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        clipboardRepository = ClipboardRepository(application)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        startForeground(FOREGROUND_NOTIFICATION_ID, notification)

        updateClipboardData()

        return START_NOT_STICKY

    }

    override fun onDestroy() {
        serviceScope.cancel()
        stopSelf()
        super.onDestroy()
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    private fun updateClipboardData() {
        clipboardManager.addPrimaryClipChangedListener {
            val primaryClipData = clipboardManager.primaryClip
            if(primaryClipData!=null || (primaryClipData?.itemCount ?: 0 != 0 && primaryClipData?.getItemAt(0)?.text ?: "" != "")) {
                insertClipData(ClipboardData(Date(), primaryClipData?.getItemAt(0)?.text.toString()))
            }
        }
    }

    private fun insertClipData(clipboardData: ClipboardData) {
        serviceScope.launch {
            clipboardRepository.insertClipData(clipboardData)
        }
    }
}