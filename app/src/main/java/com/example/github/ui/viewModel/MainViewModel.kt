package com.example.github.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.github.data.remote.GithubApiState
import com.example.github.data.remote.Status
import com.example.github.data.remote.model.TrendingRepoResponseModel
import com.example.github.repo.TrendingRepoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val trendingRepoRepository: TrendingRepoRepository): ViewModel() {

    private val githubApiState = MutableStateFlow(
        GithubApiState(
            Status.LOADING,
            emptyList<TrendingRepoResponseModel>(), ""
        )
    )


    fun getTrendingRepo() {

        githubApiState.value = GithubApiState.loading()

       viewModelScope.launch {
           trendingRepoRepository.getTrendingRepo()
               .catch {
                   githubApiState.value =
                       GithubApiState.error(it.message.toString())
               }
               .collect {
                   githubApiState.value = GithubApiState.success(it.data)
               }
       }
   }

}