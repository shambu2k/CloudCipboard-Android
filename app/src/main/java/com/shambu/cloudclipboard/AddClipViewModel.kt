package com.shambu.cloudclipboard

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.shambu.cloudclipboard.model.ClipboardData
import com.shambu.cloudclipboard.model.ClipboardRepository
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class AddClipViewModel(application: Application) : AndroidViewModel(application), CoroutineScope {

    private var clipboardRepository: ClipboardRepository
    private lateinit var insertJob: Job

    init {
        clipboardRepository = ClipboardRepository(application)
        insertJob = Job()
    }

    fun insertClipData(clipboardData: ClipboardData) {
        launch {
            clipboardRepository.insertClipData(clipboardData)
        }
    }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO + insertJob

    override fun onCleared() {
        insertJob.cancel()
        super.onCleared()
    }

}