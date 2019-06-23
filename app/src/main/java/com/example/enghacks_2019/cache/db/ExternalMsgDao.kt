package com.example.enghacks_2019.cache.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.enghacks_2019.cache.ExternalMsg

@Dao
interface ExternalMsgDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(msg: ExternalMsg)

    @Query("DELETE FROM msg_table")
    fun deleteAll(): Int

    @Query("SELECT * FROM msg_table ORDER BY time_stamp DESC LIMIT 1")
    fun getLatest(): ExternalMsg?

    @Query("SELECT * FROM msg_table ORDER BY time_stamp ASC")
    fun getAll(): List<ExternalMsg>
}