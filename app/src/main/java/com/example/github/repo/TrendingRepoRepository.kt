package com.example.github.repo

import com.example.github.data.remote.GithubApiState
import com.example.github.data.remote.GithubService
import com.example.github.data.remote.model.TrendingRepoResponseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class TrendingRepoRepository @Inject constructor(private val githubService: GithubService){
    suspend fun getTrendingRepo(): Flow<GithubApiState<MutableList<TrendingRepoResponseModel>>> {
        return flow {
            // get the comment Data from the api
            val trendingRepo = githubService.searchTrendingRepositories()

            // Emit this data wrapped in
            // the helper class [GithubApiState]
            emit(GithubApiState.success(trendingRepo))
        }.flowOn(Dispatchers.IO)
    }
}