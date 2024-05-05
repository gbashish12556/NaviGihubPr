package com.example.navigithubpr.home

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.navigithubpr.data.HomeIntent
import com.example.navigithubpr.data.HomeState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import com.example.navigithubpr.data.UserInput
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {
    private val _state = MutableStateFlow(
        HomeState(
            orgName = "android",
            folderName = "architecture-samples",
            prStatus = "all"
        )
    )
    val state: StateFlow<HomeState> = _state

    fun processIntent(intent: HomeIntent) {
        when (intent) {
            is HomeIntent.SubmitData -> submitData()
            is HomeIntent.UpdateOrgName -> _state.value =
                _state.value.copy(orgName = intent.orgName)

            is HomeIntent.UpdateFolderName -> _state.value =
                _state.value.copy(folderName = intent.folderName)

            is HomeIntent.UpdatePrStatus -> _state.value =
                _state.value.copy(prStatus = intent.prStatus)
        }
    }

    private fun submitData() {
        val userInput = UserInput(
            orgname = _state.value.orgName,
            folderName = _state.value.folderName,
            prStatus = _state.value.prStatus
        )
        if (validateInput(userInput)) {
            _state.value = _state.value.copy(userInput = userInput, isValid = true, error = null)
        } else {
            _state.value = _state.value.copy(isValid = false, error = "Invalid input")
        }
    }

    private fun validateInput(userInput: UserInput): Boolean {
        return !(userInput.orgname.isNullOrBlank() ||
                userInput.folderName.isNullOrBlank() ||
                userInput.prStatus.isNullOrBlank())
    }
}
