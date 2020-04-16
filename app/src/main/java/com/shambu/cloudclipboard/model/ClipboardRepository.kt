package com.shambu.cloudclipboard.model

import android.app.Application
import androidx.lifecycle.LiveData

class ClipboardRepository(application: Application) {

    private var clipboardDao: ClipboardDataDao

    init {
        val clipboardDatabase: ClipboardDatabase = ClipboardDatabase.getInstance(application)
        clipboardDao = clipboardDatabase.clipboardDataDao()
    }


    fun getAllClipData(): LiveData<List<ClipboardData>> {
        return clipboardDao.observeAllData()
    }

    suspend fun getClipDataByID(id: Int): ClipboardData =
        clipboardDao.getDataById(id)

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