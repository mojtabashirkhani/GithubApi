package com.example.github.util

import androidx.room.TypeConverter
import com.example.github.data.remote.model.TrendingRepoResponseModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class DataConverter {

    @TypeConverter
    fun mapItemListToString(objects: List<TrendingRepoResponseModel?>?): String? {
        val gson = Gson()
        return gson.toJson(objects)
    }

    @TypeConverter
    fun mapItemStringToList(data: String?): List<TrendingRepoResponseModel?>? {
        val listType = object : TypeToken<List<TrendingRepoResponseModel?>?>() {}.type
        return Gson().fromJson<List<TrendingRepoResponseModel?>>(data, listType)
    }
}