package com.example.navigithubpr.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.navigithubpr.data.UserInput
import com.example.navigithubpr.data.source.PrRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel()
{

    private val _dataSubmitted = MutableLiveData<UserInput>()
    val dataSubmitted: LiveData<UserInput> = _dataSubmitted

    // Two-way databinding, exposing MutableLiveData
    val orgName = MutableLiveData<String>("android")

    // Two-way databinding, exposing MutableLiveData
    val folderName = MutableLiveData<String>("architecture-samples")

    // Two-way databinding, exposing MutableLiveData
    val prStatus = MutableLiveData<String>("all")


    inline private fun onDataSubmit(userInput:UserInput) {
        _dataSubmitted.value = userInput
    }

    fun submitInput() {
        val org = orgName.value
        val folder = folderName.value
        val status = prStatus.value
        val userInput = UserInput(org ,folder, status)
        onDataSubmit(userInput)
    }

}