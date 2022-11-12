package com.example.iicnmademo.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.iicnmademo.data.local.record.RecordEntity
import com.example.iicnmademo.data.local.record.RecordKeysEntity
import com.example.iicnmademo.data.local.record.RecordDao

@Database(
    entities = [
        RecordEntity::class,
        RecordKeysEntity::class,
    ],
    version = 1,
    exportSchema = false
)
abstract class IICnmaDemoDatabase : RoomDatabase() {
    abstract fun recordDao(): RecordDao
}