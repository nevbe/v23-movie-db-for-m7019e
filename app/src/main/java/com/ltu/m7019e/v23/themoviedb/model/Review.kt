package com.ltu.m7019e.v23.themoviedb.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize


@Parcelize
data class Review(
    @Json(name = "id")
    var id: String,

    @Json(name = "author")
    var author: String,

    @Json(name = "content")
    var content: String,

    @Json(name = "url")
    var url: String,

    @Json(name = "created_at")
    var createdAt: String,

    @Json(name = "updated_at")
    var updatedAt: String
) : Parcelable
