package com.example.iicnmademo.data.local.common

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.iicnmademo.data.local.record.RecordEntity

interface CommonMovieListDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllRecord(movies: List<RecordEntity>)
}