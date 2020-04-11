package com.shambu.cloudclipboard.model

import android.app.Application
import androidx.lifecycle.LiveData

class ClipboardRepository(application: Application) {

    private var clipboardDao: ClipboardDataDao
    private var application: Application

    init {
        this.application = application
        var clipboardDatabase: ClipboardDatabase = ClipboardDatabase.getInstance(application)
        clipboardDao = clipboardDatabase.clipboardDataDao()
    }


    fun getAllClipData(): LiveData<List<ClipboardData>> {
        return clipboardDao.observeAllData()
    }

    suspend fun insertClipData(clipboardData: ClipboardData) {
        clipboardDao.insert(clipboardData)
    }

    suspend fun updateClipData(clipboardData: ClipboardData) {
        clipboardDao.update(clipboardData)
    }

    suspend fun deleteClipData(clipboardData: ClipboardData) {
        clipboardDao.delete(clipboardData)
    }

}