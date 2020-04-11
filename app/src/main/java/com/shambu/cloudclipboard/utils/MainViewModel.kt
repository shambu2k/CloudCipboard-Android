package com.shambu.cloudclipboard.utils

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.shambu.cloudclipboard.model.ClipboardData
import com.shambu.cloudclipboard.model.ClipboardRepository

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private var clipboardRepository: ClipboardRepository

    init {
        clipboardRepository =  ClipboardRepository(application)
    }

    fun getAllClipData(): LiveData<List<ClipboardData>> {
        return clipboardRepository.getAllClipData()
    }
}