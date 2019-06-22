package com.example.enghacks_2019.cache

import com.example.enghacks_2019.cache.db.ExternalMsgDao
import com.example.enghacks_2019.cache.Result.Success
import com.example.enghacks_2019.cache.Result.Error
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MsgRepository(
    private val msgDao: ExternalMsgDao

) : Repository {

    override suspend fun insert(msg: ExternalMsg): Result<Boolean> = withContext(Dispatchers.IO) {
        try {
            msgDao.insert(msg)
            return@withContext Success<Boolean>(true)
        } catch (ex: Exception) {
            return@withContext Error(ex)
        }
    }

    override suspend fun deleteAll(): Result<Int> = withContext(Dispatchers.IO) {
        try {
            val rowsAffected = msgDao.deleteAll()
            when (rowsAffected) {
                0 -> return@withContext Error(Exception("Nothing deleted."))
                else -> return@withContext Success<Int>(rowsAffected)
            }
        } catch (ex: Exception) {
            return@withContext Error(ex)
        }
    }

    override suspend fun getLatest(): Result<ExternalMsg> = withContext(Dispatchers.IO) {
        try {
            val result = msgDao.getLatest()
            if (result != null) {
                return@withContext Success<ExternalMsg>(result)
            } else {
                return@withContext Error(Exception("Note not found!"))
            }
        } catch (ex: Exception) {
            return@withContext Error(ex)
        }
    }

    override suspend fun getAll(): Result<List<ExternalMsg>> = withContext(Dispatchers.IO) {
        try {
            return@withContext Success<List<ExternalMsg>>(msgDao.getAll())
        } catch (ex: Exception) {
            return@withContext Error(ex)
        }
    }
}