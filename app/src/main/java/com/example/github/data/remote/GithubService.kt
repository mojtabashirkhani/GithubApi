package com.example.github.data.remote

import com.example.github.data.remote.model.TrendingRepoResponseModel
import retrofit2.http.GET

interface GithubService {

    @GET("repositories")
    suspend fun searchTrendingRepositories(): MutableList<TrendingRepoResponseModel>
}