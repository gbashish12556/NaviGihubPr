package com.example.navigithubpr.prlist

import androidx.lifecycle.*
import com.example.navigithubpr.common.Resource
import com.example.navigithubpr.utils.NetworkHelper
import com.example.navigithubpr.home.UserInput
import com.example.navigithubpr.data.response.GithubIssuesResponse
import com.example.navigithubpr.data.source.PrRepository
import com.example.navigithubpr.usecases.GetPrListUseCase
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class PrViewModel @Inject
constructor(val prListUseCase: GetPrListUseCase,
                    savedStateHandle:SavedStateHandle) : ViewModel()
{

    private val _uiState = MutableStateFlow(PrListState())
    val uiState = _uiState

    init {

        var userInput = Gson().fromJson<UserInput>(savedStateHandle.get<String>("prDetail"), UserInput::class.java)
        fetchUsers(userInput)

    }

    fun fetchUsers(userInput: UserInput) {

        viewModelScope.launch {

            val coins  = prListUseCase(userInput = userInput)

            coins.collect {result->
                when(result){
                    is Resource.Success->{
                        _uiState.value = PrListState(list = result.data?: emptyList())
                    }
                    is Resource.Error->{
                        _uiState.value = PrListState(error = result.message?:"Something went wrong")
                    }
                    is Resource.Loading->{
                        _uiState.value = PrListState(isLoading = true)
                    }
                }
            }

        }

    }

}