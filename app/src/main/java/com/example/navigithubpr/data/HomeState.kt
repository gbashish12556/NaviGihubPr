package com.example.navigithubpr.data


data class HomeState(
    val orgName: String? = null,
    val folderName: String? = null,
    val prStatus: String? = null,
    val userInput: UserInput? = null,
    val isValid: Boolean = true,
    val error: String? = null
)

sealed class HomeIntent {
    object SubmitData : HomeIntent()
    data class UpdateOrgName(val orgName: String) : HomeIntent()
    data class UpdateFolderName(val folderName: String) : HomeIntent()
    data class UpdatePrStatus(val prStatus: String) : HomeIntent()
}
