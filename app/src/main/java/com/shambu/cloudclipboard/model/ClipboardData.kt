package com.shambu.cloudclipboard.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "clipboard_data")
data class ClipboardData constructor(@ColumnInfo(name = "date") var date: Date,
                                     @ColumnInfo(name = "text_data") var clipboardText: String = "",
                                     @PrimaryKey(autoGenerate = true) var dataId: Int = 0
) {

    val getDate: String
        get() = date.toString()

}