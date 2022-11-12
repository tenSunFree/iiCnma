package com.example.iicnmademo.data.local.record

import androidx.paging.PagingSource
import androidx.room.*
import com.example.iicnmademo.data.local.common.CommonMovieListDao
import com.example.iicnmademo.data.remote.STARTING_PAGE_INDEX
import com.example.iicnmademo.data.remote.home.HomeObjectResponse
import com.example.iicnmademo.data.repository.home.HomeLocalDataSource

@Dao
interface RecordDao : CommonMovieListDao, HomeLocalDataSource {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(remoteKey: List<RecordKeysEntity>)

    @Query("SELECT * FROM record_keys WHERE movieId = :id")
    override suspend fun getRemoteKeysForMovieId(id: Long): RecordKeysEntity?

    @Query("DELETE FROM record_keys")
    suspend fun clearRemoteKeys()

    @Query(
        """SELECT record.* FROM record
    INNER JOIN record_keys ON record.id=record_keys.movieId"""
    )
    override fun getAll(): PagingSource<Int, RecordEntity>

    @Transaction
    override suspend fun cacheResponse(
        response: HomeObjectResponse,
        pageKey: Int,
        isRefresh: Boolean,
    ) {
        if (isRefresh) clearRemoteKeys()
        val prevKey = if (pageKey == STARTING_PAGE_INDEX) null else pageKey - 1
        val nextKey = if (pageKey >= response.infoResponse.pages) null else pageKey + 1
        val keys = response.recordResponses.map {
            RecordKeysEntity(it.id.toLong(), prevKey, pageKey, nextKey)
        }
        insertAll(keys)
        insertAllRecord(response.toRecordEntityList())
    }
}