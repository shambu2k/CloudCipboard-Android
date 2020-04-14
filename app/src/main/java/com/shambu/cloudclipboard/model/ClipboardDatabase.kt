package com.shambu.cloudclipboard.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.shambu.cloudclipboard.utils.Converter

@Database(entities = [ClipboardData::class], version = 1, exportSchema = false)
@TypeConverters(Converter::class)
abstract class ClipboardDatabase: RoomDatabase() {
    abstract fun clipboardDataDao(): ClipboardDataDao

    companion object {

        @Volatile
        private var INSTANCE: ClipboardDatabase? = null

        fun getInstance(context: Context): ClipboardDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if(instance==null){
                    instance = Room.databaseBuilder(context.applicationContext,
                        ClipboardDatabase::class.java, "clipboard_database")
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }

        }
    }
}