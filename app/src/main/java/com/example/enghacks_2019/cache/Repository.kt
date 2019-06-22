package com.example.enghacks_2019.cache

interface Repository {

    suspend fun insert(msg: ExternalMsg): Result<Boolean>

    suspend fun deleteAll(): Result<Int>

    suspend fun getLatest(): Result<ExternalMsg>

    suspend fun getAll(): Result<List<ExternalMsg>>
}