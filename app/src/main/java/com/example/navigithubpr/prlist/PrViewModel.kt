package com.example.navigithubpr.prlist

import androidx.lifecycle.*
import com.example.navigithubpr.utils.NetworkHelper
import com.example.navigithubpr.home.UserInput
import com.example.navigithubpr.data.response.GithubIssuesResponse
import com.example.navigithubpr.data.source.PrRepository
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class PrViewModel
@Inject constructor(var prRepository: PrRepository,
                    private val networkHelper: NetworkHelper,
                    savedStateHandle:SavedStateHandle) : ViewModel()
{

    private val _dataLoading = MutableLiveData<Boolean>(false)
    val dataLoading: LiveData<Boolean> = _dataLoading

    private val _isDataLoadingError = MutableLiveData<Pair<Boolean,String>>(Pair(false,""))
    val isDataLoadingError: LiveData<Pair<Boolean,String>> = _isDataLoadingError


    private val _users = MutableLiveData<List<GithubIssuesResponse>>()
    val items: LiveData<List<GithubIssuesResponse>>
        get() = _users

    init {
        var userInput = Gson().fromJson<UserInput>(savedStateHandle.get<String>("prDetail"), UserInput::class.java)
        fetchUsers(userInput)
    }

    fun fetchUsers(userInputState: UserInput) {
        viewModelScope.launch {
            if (networkHelper.isNetworkConnected()) {
                _dataLoading.value = true;
                prRepository.getTasks(userInputState).let {
                    _dataLoading.value  = false;
                    if (it.isSuccessful) {
                        _isDataLoadingError.value = Pair(false,"")
                        _users.postValue(it.body())
                    } else {
                        _isDataLoadingError.value = Pair(true,"No Match Found")
                    }
                }
            }
            else {
                _isDataLoadingError.value = Pair(true,"Slow Internet")
            }
        }
    }

}