package com.example.navigithubpr.prlist


import android.content.Context
import android.util.Log
import android.view.ViewGroup
import android.widget.ImageView
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.bumptech.glide.Glide
import com.example.navigithubpr.data.UserInput
import com.example.navigithubpr.data.response.GithubIssuesResponse
import com.google.gson.Gson

@Composable
fun PrListScreen(
    navController: NavController,
    userInputJson: String,
    viewModel: PrViewModel = hiltViewModel()
) {

    val userInput = Gson().fromJson(userInputJson, UserInput::class.java)
    val state by viewModel.state.collectAsState()

    Log.d("AshishGupta1", userInputJson)

    LaunchedEffect(userInput) {
        viewModel.fetchPullRequests(userInput)
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("PR List") })
        },
        content = { padding ->
            Box(modifier = Modifier.padding(padding)) {
                if (state.isLoading) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                } else {
                    LazyColumn {
                        items(state.items) { item ->
                            PrItem(item)
                        }
                    }
                }
                state.error?.let { error ->
                    Text(
                        text = error,
                        color = MaterialTheme.colors.error,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
        }
    )
}

@Composable
fun PrItem(pr: GithubIssuesResponse) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        elevation = 4.dp,
        shape = RoundedCornerShape(10.dp)
    ) {
        Column(modifier = Modifier.padding(5.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                pr.user?.avatarUrl?.let {
                    GlideImage(
                        url = it,
                        modifier = Modifier
                            .size(100.dp)
                            .padding(5.dp)
                    )
                }
                Column {
                    Text(text = "Title: ${pr.title}", style = MaterialTheme.typography.h6)
                    Text(
                        text = "UserName: ${pr.user?.login}",
                        style = MaterialTheme.typography.body2
                    )
                    Text(text = "OpenDate: ${pr.createdAt}", style = MaterialTheme.typography.body2)
                    pr.closedAt?.let {
                        Text(text = "Closed Date: $it", style = MaterialTheme.typography.body2)
                    }
                }
            }
        }
    }
}


@Composable
fun GlideImage(
    url: String,
    modifier: Modifier = Modifier,
    context: Context = LocalContext.current
) {
    AndroidView(
        factory = { ctx ->
            ImageView(ctx).apply {
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
            }
        },
        modifier = modifier,
        update = { imageView ->
            Glide.with(context)
                .load(url)
                .into(imageView)
        }
    )
}

