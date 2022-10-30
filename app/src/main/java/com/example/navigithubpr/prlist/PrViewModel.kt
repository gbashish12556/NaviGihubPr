package com.example.navigithubpr.prlist

import androidx.lifecycle.*
import com.example.navigithubpr.data.UserInput
import com.example.navigithubpr.data.response.GithubIssuesResponse
import com.example.navigithubpr.data.source.PrRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class PrViewModel
@Inject constructor(var prRepository: PrRepository) : ViewModel()
{

    private val _updateData = MutableLiveData<UserInput>(UserInput("android","architecture-samples","all"))

    private val _dataLoading = MutableLiveData<Boolean>(false)
    val dataLoading: LiveData<Boolean> = _dataLoading

    private val _isDataLoadingError = MutableLiveData<Boolean>(false)
    val isDataLoadingError = _isDataLoadingError

    private val _items: LiveData<List<GithubIssuesResponse>> = _updateData.switchMap { userInput ->
            _dataLoading.value = true
            viewModelScope.launch {
//                prRepository.refreshTask(userInput)
//                _dataLoading.value = false
            }
            prRepository.getTasks(userInput).switchMap {
                filterPrs(it)
            }
    }

    val items: LiveData<List<GithubIssuesResponse>> = _items

    private fun filterPrs(tasksResult: List<GithubIssuesResponse>): MutableLiveData<List<GithubIssuesResponse>> {
        val result = MutableLiveData<List<GithubIssuesResponse>>()
        result.postValue(tasksResult)
        return result
    }

    public fun refreshTask(userInput: UserInput){
        _updateData.value = userInput
    }


}