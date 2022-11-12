package com.example.iicnmademo.data.repository.home

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.map
import com.example.iicnmademo.data.remote.NETWORK_PAGE_SIZE
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class HomeRepository @Inject constructor(
    private val localDS: HomeLocalDataSource,
    private val remoteDS: HomeRemoteDataSource,
) {

    @OptIn(ExperimentalPagingApi::class)
    fun getPopularMovies() = Pager(
        config = PagingConfig(
            pageSize = NETWORK_PAGE_SIZE,
            enablePlaceholders = false,
        ),
        remoteMediator = HomeRemoteMediator(remoteDS, localDS),
        pagingSourceFactory = { localDS.getAll() }
    ).flow.map { pagingData ->
        pagingData.map { it.toRecord() }
    }
}