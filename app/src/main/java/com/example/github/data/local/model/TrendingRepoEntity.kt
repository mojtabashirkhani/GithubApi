package com.example.github.data.local.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.github.data.remote.model.TrendingRepoResponseModel
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue


@Parcelize
@Entity(tableName = "Trending")
data class TrendingRepoEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int,


    @ColumnInfo(name = "value")
    val value: @RawValue List<TrendingRepoResponseModel>?
): Parcelable {
    constructor(value: List<TrendingRepoResponseModel>?) : this(0, value)
}