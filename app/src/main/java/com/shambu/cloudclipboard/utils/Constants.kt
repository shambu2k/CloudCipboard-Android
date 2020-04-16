package com.shambu.cloudclipboard.utils

class Constants {
    companion object {
        const val FOREGROUND_SERVICE_CHANNEL_ID: String = "ClipboardService"
        const val FOREGROUND_SERVICE_PENDINGINTENT_RC: Int = 0
        const val FOREGROUND_SERVICE_PENDINGINTENT_FLAGS: Int = 0
        const val FOREGROUND_NOTIFICATION_ID = 1
        const val SHARED_PREF_FILE = "Cloud Clipboard"
        const val SHARED_PREF_SERVICE_STATE_KEY = "SERVICE_STATE"
    }
}