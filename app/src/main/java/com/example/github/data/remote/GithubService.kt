package com.example.github.data.remote

import com.example.github.data.remote.model.TrendingRepoResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubService {

    @GET("repositories")
    suspend fun searchTrendingRepositories(): MutableList<TrendingRepoResponse>
}