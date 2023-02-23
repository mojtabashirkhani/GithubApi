package com.example.github.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.example.github.data.local.model.TrendingRepoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TrendingRepoDao {

    @Transaction
    suspend fun updateTrendingRepos(entities: MutableList<TrendingRepoEntity>) {
        entities.let {
            deleteTrendingRepos()
            insertTrendingRepos(it)
        }
    }

    @Insert
    suspend fun insertTrendingRepos(entities: List<TrendingRepoEntity>)

    @Query("DELETE FROM Trending")
    suspend fun deleteTrendingRepos()

    @Query("SELECT * FROM Trending")
    fun getTrendingRepos(): Flow<List<TrendingRepoEntity>>
}