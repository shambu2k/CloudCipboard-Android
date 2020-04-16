package com.shambu.cloudclipboard

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.shambu.cloudclipboard.model.ClipboardData
import com.shambu.cloudclipboard.model.ClipboardRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class AddClipViewModel(application: Application) : AndroidViewModel(application) {

    private var clipboardRepository: ClipboardRepository = ClipboardRepository(application)
    private val viewModelScope: CoroutineScope = CoroutineScope(Dispatchers.IO)

    fun insertClipData(clipboardData: ClipboardData) {
        viewModelScope.launch {
            clipboardRepository.insertClipData(clipboardData)
        }
    }

    fun getClipDataById(id: Int): LiveData<ClipboardData> =
         liveData {
            val data = clipboardRepository.getClipDataByID(id)
            emit(data)
        }


    fun updateClipData(clipboardData: ClipboardData) {
        viewModelScope.launch {
            clipboardRepository.updateClipData(clipboardData)
        }
    }

    fun deleteClipData(clipboardData: ClipboardData) {
        viewModelScope.launch {
            clipboardRepository.deleteClipData(clipboardData)
        }
    }

    override fun onCleared() {
        viewModelScope.cancel()
        super.onCleared()
    }
}