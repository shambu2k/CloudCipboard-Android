package com.shambu.cloudclipboard.utils

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.shambu.cloudclipboard.utils.Constants.Companion.SHARED_PREF_FILE
import com.shambu.cloudclipboard.utils.Constants.Companion.SHARED_PREF_SERVICE_STATE_KEY

class SharedPrefWrapper(application: Application) {
    private val pref: SharedPreferences

    init {
        pref = application.getSharedPreferences(SHARED_PREF_FILE, Context.MODE_PRIVATE)
    }

    fun toggleServiceState(state: Boolean) {
        val editor = pref.edit()
        editor.putBoolean(SHARED_PREF_SERVICE_STATE_KEY, state)
        editor.apply()
    }

    fun isRunning(): Boolean = pref.getBoolean(SHARED_PREF_SERVICE_STATE_KEY, false)
}