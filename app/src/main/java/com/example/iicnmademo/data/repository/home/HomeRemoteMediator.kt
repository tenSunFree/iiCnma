package com.example.iicnmademo.data.repository.home

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.bumptech.glide.load.HttpException
import com.example.iicnmademo.data.local.record.RecordEntity
import com.example.iicnmademo.data.local.record.RecordKeysEntity
import com.example.iicnmademo.data.remote.NETWORK_PAGE_SIZE
import com.example.iicnmademo.data.remote.STARTING_PAGE_INDEX
import com.example.iicnmademo.domain.model.utils.NetworkError
import com.example.iicnmademo.domain.model.utils.UnknownError
import timber.log.Timber
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class HomeRemoteMediator(
    private val remote: HomeRemoteDataSource,
    private val local: HomeLocalDataSource,
) : RemoteMediator<Int, RecordEntity>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, RecordEntity>,
    ): MediatorResult {
        val pageKey = when (loadType) {
            LoadType.REFRESH -> {
                val remoteKey = getRemoteKeyClosestToCurrentPosition(state)
                remoteKey?.nextKey?.minus(1) ?: STARTING_PAGE_INDEX
            }
            LoadType.PREPEND -> {
                val remoteKey = getRemoteKeyForFirstItem(state)
                remoteKey?.prevKey
                    ?: return MediatorResult.Success(endOfPaginationReached = remoteKey != null)
            }
            LoadType.APPEND -> {
                val remoteKey = getRemoteKeyForLastItem(state)
                remoteKey?.nextKey
                    ?: return MediatorResult.Success(endOfPaginationReached = remoteKey != null)
            }
        }
        return try {
            val apiResponse = remote.getObject(NETWORK_PAGE_SIZE, pageKey)
            local.cacheResponse(apiResponse, pageKey, loadType == LoadType.REFRESH)
            MediatorResult.Success(endOfPaginationReached = pageKey >= apiResponse.infoResponse.pages)
        } catch (exception: IOException) {
            MediatorResult.Error(UnknownError(exception))
        } catch (exception: HttpException) {
            MediatorResult.Error(NetworkError(exception))
        }
    }

    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, RecordEntity>): RecordKeysEntity? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { movie -> local.getRemoteKeysForMovieId(movie.id) }
    }

    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, RecordEntity>): RecordKeysEntity? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { movie -> local.getRemoteKeysForMovieId(movie.id) }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(state: PagingState<Int, RecordEntity>): RecordKeysEntity? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)
                ?.id?.let { movieId -> local.getRemoteKeysForMovieId(movieId) }
        }
    }
}