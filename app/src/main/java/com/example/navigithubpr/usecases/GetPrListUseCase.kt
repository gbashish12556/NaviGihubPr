package com.example.navigithubpr.usecases

import androidx.compose.runtime.collectAsState
import com.example.navigithubpr.common.Resource
import com.example.navigithubpr.data.response.GithubIssuesResponse
import com.example.navigithubpr.data.source.PrRepository
import com.example.navigithubpr.home.UserInput
import com.example.navigithubpr.utils.NetworkHelper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetPrListUseCase @Inject constructor(
    var prRepository: PrRepository,
): BaseCase<Flow<List<GithubIssuesResponse>>, UserInput>()  {

    override suspend fun invoke(userInput: UserInput?) =  prRepository.getTasks(userInput!!)

}