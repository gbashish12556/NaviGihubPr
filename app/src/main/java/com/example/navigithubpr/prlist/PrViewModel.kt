package com.example.navigithubpr.prlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.navigithubpr.NetworkHelper
import com.example.navigithubpr.data.UserInput
import com.example.navigithubpr.data.response.GithubIssuesResponse
import com.example.navigithubpr.data.source.PrRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PrViewModel @Inject constructor(
    @PrViewModelScope private val prRepository: PrRepository,
    private val networkHelper: NetworkHelper
) : ViewModel() {

    private val _state = MutableStateFlow(PrListState())
    val state: StateFlow<PrListState> = _state

    fun dispatch(intent: PrListIntent) {
        when (intent) {
            is PrListIntent.FetchData -> fetchPullRequests(intent.userInput)
        }
    }

    fun fetchPullRequests(userInput: UserInput) {
        viewModelScope.launch {
            if (networkHelper.isNetworkConnected()) {
                _state.value = _state.value.copy(isLoading = true, error = null)
                try {
                    val response = prRepository.getTasks(userInput)
                    if (response.isSuccessful) {
                        _state.value = _state.value.copy(
                            isLoading = false,
                            items = response.body() ?: emptyList(),
                            error = null
                        )
                    } else {
                        _state.value = _state.value.copy(
                            isLoading = false,
                            error = "No Match Found"
                        )
                    }
                } catch (e: Exception) {
                    _state.value = _state.value.copy(
                        isLoading = false,
                        error = e.message ?: "An error occurred"
                    )
                }
            } else {
                _state.value = _state.value.copy(
                    isLoading = false,
                    error = "Slow Internet"
                )
            }
        }
    }
}

data class PrListState(
    val isLoading: Boolean = false,
    val items: List<GithubIssuesResponse> = emptyList(),
    val error: String? = null
)

sealed class PrListIntent {
    data class FetchData(val userInput: UserInput) : PrListIntent()
}
