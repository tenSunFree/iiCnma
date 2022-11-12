package com.example.iicnmademo.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Record(
    val id: Long,
    val title: String,
    val accessionyear: Int,
    val primaryimageurl: String,
) : Parcelable