package com.example.iicnmademo.data.local.record

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "record_keys")
data class RecordKeysEntity(
    @PrimaryKey @field:SerializedName("record_id") val movieId: Long,
    @field:SerializedName("prev_key") val prevKey: Int?,
    @field:SerializedName("cur_key") val curKey: Int,
    @field:SerializedName("next_key") val nextKey: Int?,
)