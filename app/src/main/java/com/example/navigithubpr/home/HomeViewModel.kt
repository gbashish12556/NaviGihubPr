package com.example.navigithubpr.home

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel()
{

    private var _uiState = MutableStateFlow(UserInput(orgName="android", folderName = "architecture-samples", prStatus = "all"))
    var uiState = _uiState

    fun updateOrg(orgName:String){
        _uiState.update {
            it.copy(orgName=orgName)
        }
    }

    fun updateFolder(folderName:String){
        _uiState.update {
            it.copy(folderName=folderName)
        }
    }

    fun updatePrStatus(prStatus:String){
        _uiState.update {
            it.copy(prStatus = prStatus)
        }
    }


    fun submitInput() {

    }

}