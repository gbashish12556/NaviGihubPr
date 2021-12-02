package com.example.navigithubpr.prlist

import android.util.Log
import androidx.lifecycle.*
import com.example.navigithubpr.data.Result
import com.example.navigithubpr.data.UserInput
import com.example.navigithubpr.data.response.GithubIssuesResponse
import com.example.navigithubpr.data.source.PrRepository
import kotlinx.coroutines.*
import retrofit2.await

class PrViewModel(private val prRepository: PrRepository) : ViewModel() {

    private val _dataLoading = MutableLiveData<Boolean>(false)
    val dataLoading: LiveData<Boolean> = _dataLoading

    private val _isDataLoadingError = MutableLiveData<Boolean>(false)
    val isDataLoadingError = _isDataLoadingError

    private val _items = MutableLiveData<List<GithubIssuesResponse>>()
    val items: LiveData<List<GithubIssuesResponse>> = _items


    var job: Job? = null
    val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        noMatchFound()
    }

    fun noMatchFound(){
        _items.postValue(emptyList())
        _isDataLoadingError.postValue(true)
    }
    fun loadPr(userInput: UserInput) {
        _dataLoading.postValue(true)
        job = viewModelScope.launch(Dispatchers.IO+exceptionHandler) {
            val response = prRepository.getTasks(userInput).await()
            withContext(Dispatchers.Main) {
                _dataLoading.postValue(false)
                if (response is List<GithubIssuesResponse>) {
                    _items.postValue(response)
                } else {
                    noMatchFound()
                }
            }
        }
    }

}