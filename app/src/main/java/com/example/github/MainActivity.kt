package com.example.github

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.github.data.remote.Status
import com.example.github.ui.theme.GithubTheme
import com.example.github.ui.viewModel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GithubTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }

        // initialize viewModel
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        viewModel.getTrendingRepo()

        lifecycleScope.launch {
            viewModel.githubApiState.collect {
                when (it.status) {
                    Status.LOADING -> {
                        Log.d("MainActivity", "Loading")

                    }
                    Status.SUCCESS -> {
                        Log.d("MainActivity", "success: " + it.data?.size.toString())

                    }
                    Status.ERROR -> {
                        Log.d("MainActivity", "error: "+ it.message.toString())

                    }
                }
            }
        }

    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    GithubTheme {
        Greeting("Android")
    }
}