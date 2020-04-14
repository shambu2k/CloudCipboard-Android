package com.shambu.cloudclipboard

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.shambu.cloudclipboard.model.ClipboardData
import com.shambu.cloudclipboard.model.ClipboardRepository
import kotlinx.coroutines.*

class AddClipViewModel(application: Application) : AndroidViewModel(application) {

    private var clipboardRepository: ClipboardRepository = ClipboardRepository(application)
    private var insertJob: Job = Job()
    private var getByIdJob: Job = Job()
    private var updateJob: Job = Job()
    private var deleteJob: Job = Job()
    private val insertScope: CoroutineScope
    private val updateScope: CoroutineScope
    private val getDataScope: CoroutineScope
    private val deleteScope: CoroutineScope

    init {
        insertScope = CoroutineScope(Dispatchers.IO + insertJob)
        getDataScope = CoroutineScope(Dispatchers.IO + getByIdJob)
        updateScope = CoroutineScope(Dispatchers.IO + updateJob)
        deleteScope = CoroutineScope(Dispatchers.IO + deleteJob)
    }

    fun insertClipData(clipboardData: ClipboardData) {
        insertScope.launch {
            clipboardRepository.insertClipData(clipboardData)
        }
    }

    fun getClipDataById(id: Int): LiveData<ClipboardData> {
        return liveData {
            val data = clipboardRepository.getClipDataByID(id)
            emit(data)
        }
    }

    fun updateClipData(clipboardData: ClipboardData) {
        updateScope.launch {
            clipboardRepository.updateClipData(clipboardData)
        }
    }

    fun deleteClipData(clipboardData: ClipboardData) {
        deleteScope.launch {
            clipboardRepository.deleteClipData(clipboardData)
        }
    }

    override fun onCleared() {
        insertJob.cancel()
        getByIdJob.cancel()
        updateJob.cancel()
        super.onCleared()
    }

}