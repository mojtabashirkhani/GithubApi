package com.example.github.di

import android.app.Application
import androidx.room.Room
import com.example.github.data.local.GithubDatabase
import com.example.github.data.local.GithubDatabase.Companion.DB_NAME
import com.example.github.data.local.dao.TrendingRepoDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun provideRoomDatabase(application: Application): GithubDatabase {
        return Room
            .databaseBuilder(application, GithubDatabase::class.java, DB_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideTrendingRepoDao(db: GithubDatabase): TrendingRepoDao {
        return db.trendingRepoDao()
    }

}