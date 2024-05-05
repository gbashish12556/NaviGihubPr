package com.example.navigithubpr.home

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.material.*
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.navigithubpr.data.HomeIntent
import com.google.gson.GsonBuilder
import com.example.navigithubpr.data.HomeState

private val gson by lazy { GsonBuilder().create() }

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    val navigationCommand by derivedStateOf {
        if (state.isValid && state.userInput != null) gson.toJson(state.userInput) else null
    }

    LaunchedEffect(key1 = navigationCommand) {
        navigationCommand?.let { navController.navigate("prListScreen/$it") }
    }

    FormView(state = state, processIntent = viewModel::processIntent)
}

@Composable
fun FormView(
    state: HomeState,
    processIntent: (HomeIntent) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(
                value = state.orgName ?: "",
                onValueChange = { processIntent(HomeIntent.UpdateOrgName(it)) },
                label = { Text("Organization Name") }
            )
            TextField(
                value = state.folderName ?: "",
                onValueChange = { processIntent(HomeIntent.UpdateFolderName(it)) },
                label = { Text("Folder Name") }
            )
            TextField(
                value = state.prStatus ?: "",
                onValueChange = { processIntent(HomeIntent.UpdatePrStatus(it)) },
                label = { Text("PR Status") }
            )
            Button(
                onClick = { processIntent(HomeIntent.SubmitData) },
                enabled = state.isValid
            ) {
                Text("Submit")
            }
            state.error?.let { error ->
                Text(error, color = MaterialTheme.colors.error)
            }
        }
    }
}
