package com.shambu.cloudclipboard

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.shambu.cloudclipboard.model.ClipboardData
import com.shambu.cloudclipboard.model.ClipboardRepository

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private var clipboardRepository: ClipboardRepository = ClipboardRepository(application)

    fun getAllClipData(): LiveData<List<ClipboardData>> = clipboardRepository.getAllClipData()
}