package com.example.github.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.github.data.local.GithubDatabase.Companion.VERSION
import com.example.github.data.local.dao.TrendingRepoDao
import com.example.github.data.local.model.TrendingRepoEntity
import com.example.github.util.DataConverter

@Database(
    entities = [TrendingRepoEntity::class],
    version = VERSION,
    exportSchema = false
)

@TypeConverters(DataConverter::class)
abstract class GithubDatabase: RoomDatabase() {

    abstract fun trendingRepoDao(): TrendingRepoDao

    companion object {
        const val DB_NAME = "github.db"
        const val VERSION = 1
    }
}