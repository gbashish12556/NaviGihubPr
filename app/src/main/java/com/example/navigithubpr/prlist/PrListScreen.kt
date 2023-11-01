package com.example.navigithubpr.prlist

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.navigithubpr.data.response.GithubIssuesResponse

@Composable
fun PrListScreen(
    prViewModel: PrViewModel = hiltViewModel()
){
    var uiState = prViewModel.uiState.collectAsState().value
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
        uiState.list?.let {list->
            PrList(data = list)
        }
        if(uiState.isLoading){
            CircularProgressIndicator()
        }
        if(uiState.isLoadingError){
            Text(text = uiState.error!!)
        }
    }
}

@Composable
fun PrList(data:List<GithubIssuesResponse>){
    LazyColumn(modifier = Modifier.fillMaxSize()){
        items(data){githbubResponse->
            ViewItem(githubIssuesResponse = githbubResponse)
        }
    }
}

@Composable
fun ViewItem(githubIssuesResponse: GithubIssuesResponse){
    Card(modifier = Modifier
        .padding(10.dp)
        .fillMaxWidth(),
        shape = RoundedCornerShape(5.dp)) {
        Row(modifier = Modifier.fillMaxWidth().padding(20.dp)) {
            Image(
                painter = rememberAsyncImagePainter(githubIssuesResponse.user?.avatarUrl),
                contentDescription = null,
                modifier = Modifier.size(100.dp),
            )
            Spacer(modifier = Modifier.width(10.dp))
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(text = "User  ${githubIssuesResponse.user?.login!!}")
                Text(text = "Created  ${githubIssuesResponse.createdAt}")
                Text(text = "CLosed  ${githubIssuesResponse.closedAt}")
            }
        }
    }
}