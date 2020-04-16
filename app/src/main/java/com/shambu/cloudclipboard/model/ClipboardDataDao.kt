package com.shambu.cloudclipboard.model

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ClipboardDataDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(data: ClipboardData)

    @Update
    suspend fun update(data: ClipboardData)

    @Delete
    suspend fun delete(data: ClipboardData)

    @Query("SELECT * FROM clipboard_data WHERE dataId == :id")
    suspend fun getDataById(id: Int): ClipboardData

    @Query("SELECT * FROM clipboard_data ORDER BY dataId DESC")
    fun observeAllData(): LiveData<List<ClipboardData>>

}