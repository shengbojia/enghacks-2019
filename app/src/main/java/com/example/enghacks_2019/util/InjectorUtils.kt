package com.example.enghacks_2019.util

import android.content.Context
import com.example.enghacks_2019.cache.MsgRepository
import com.example.enghacks_2019.cache.Repository
import com.example.enghacks_2019.cache.db.AppDatabase

object InjectorUtils {
    fun getNoteRepository(context: Context): Repository =
        MsgRepository.getInstance(
            AppDatabase.getInstance(context.applicationContext).msgDao()
        )
}