package com.example.iicnmademo.data.local.record

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.example.iicnmademo.domain.model.Record

@Entity(tableName = "record")
data class RecordEntity(
    @PrimaryKey
    @field:SerializedName("id") val id: Long,
    @field:SerializedName("title") val title: String,
    @field:SerializedName("accessionyear") val accessionyear: Int,
    @field:SerializedName("primaryimageurl") val primaryimageurl: String,
) {

    fun toRecord() = Record(
        id = id,
        title = title,
        accessionyear = accessionyear,
        primaryimageurl = primaryimageurl,
    )
}