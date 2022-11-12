package com.example.iicnmademo.data.repository.home

import androidx.paging.PagingSource
import com.example.iicnmademo.data.local.record.RecordEntity
import com.example.iicnmademo.data.local.record.RecordKeysEntity
import com.example.iicnmademo.data.remote.home.HomeObjectResponse

interface HomeLocalDataSource {
    fun getAll(): PagingSource<Int, RecordEntity>
    suspend fun getRemoteKeysForMovieId(id: Long): RecordKeysEntity?
    suspend fun cacheResponse(response: HomeObjectResponse, pageKey: Int, isRefresh: Boolean)
}
