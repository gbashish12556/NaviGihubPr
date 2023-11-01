package com.example.navigithubpr.home

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.navigithubpr.Screen
import com.example.navigithubpr.prlist.PrViewModel
import com.google.gson.Gson
import java.net.URLEncoder
import java.nio.charset.StandardCharsets


@Composable
fun HomeScreen(
    navController: NavController,
    homeViewModel: HomeViewModel =  hiltViewModel()
){

    var state = homeViewModel.uiState.collectAsState().value
    
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(20.dp),
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Center) {
        
        TextField(
            value = state.folderName, 
            modifier = Modifier
                .fillMaxWidth(),
            onValueChange = { homeViewModel.updateFolder(it)},
            label = {
                Text(text = "Folder Name")
            }
        )

        TextField(
            value = state.orgName,
            modifier = Modifier
                .fillMaxWidth(),
            onValueChange = { homeViewModel.updateOrg(it)},
            label = {
                Text(text = "Org Name")
            }
        )

        TextField(
            value = state.prStatus,
            modifier = Modifier
                .fillMaxWidth(),
            onValueChange = { homeViewModel.updatePrStatus(it)},
            label = {
                Text(text = "PR Status")
            }
        )
        
        Button(onClick = {
            val prDetail = Gson().toJson(state)
            var encode = URLEncoder.encode(prDetail, StandardCharsets.UTF_8.toString())
//            navController.navigate(Screen.PrListScreen.route+"/${prDetail}")
            navController.navigate(Screen.PrListScreen.route+"/$encode")
        }) {
            Text(text = "Submit")
        }
    }

}