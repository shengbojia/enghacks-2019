package com.example.enghacks_2019.cache

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(
    tableName = "msg_table"
)
data class ExternalMsg(
    val msgCode:
    @PrimaryKey val id: String = UUID.randomUUID().toString(),
    @ColumnInfo(name = "time_stamp") val timeStamp: Calendar = Calendar.getInstance()
)