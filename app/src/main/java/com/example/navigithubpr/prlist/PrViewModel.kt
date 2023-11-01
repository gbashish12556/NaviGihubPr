package com.example.navigithubpr.prlist

import androidx.lifecycle.*
import com.example.navigithubpr.utils.NetworkHelper
import com.example.navigithubpr.home.UserInput
import com.example.navigithubpr.data.response.GithubIssuesResponse
import com.example.navigithubpr.data.source.PrRepository
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class PrViewModel
@Inject constructor(var prRepository: PrRepository,
                    private val networkHelper: NetworkHelper,
                    savedStateHandle:SavedStateHandle) : ViewModel()
{

    private val _uiState = MutableStateFlow(PrListState())
    val uiState = _uiState

    init {
        var userInput = Gson().fromJson<UserInput>(savedStateHandle.get<String>("prDetail"), UserInput::class.java)
        fetchUsers(userInput)
    }

    fun fetchUsers(userInputState: UserInput) {
        viewModelScope.launch {
            if (networkHelper.isNetworkConnected()) {
                _uiState.update {
                    it.copy(isLoading = true)
                }
                prRepository.getTasks(userInputState).let {response->
                    _uiState.update {
                        it.copy(isLoading = false)
                    }
                    if (response.isSuccessful) {
                        _uiState.update {liststate->
                            liststate.copy(isLoading = false, isLoadingError = false, error = "", list = response.body())
                        }
                    } else {
                        _uiState.update {
                            it.copy(isLoadingError = true, error = "No match found")
                        }
                    }
                }
            }
            else {
                _uiState.update {
                    it.copy(isLoadingError = true, error = "Slow internet")
                }
            }
        }
    }

}