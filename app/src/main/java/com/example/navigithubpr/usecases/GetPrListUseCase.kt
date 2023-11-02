package com.example.navigithubpr.usecases

import com.example.navigithubpr.common.Resource
import com.example.navigithubpr.data.response.GithubIssuesResponse
import com.example.navigithubpr.data.source.PrRepository
import com.example.navigithubpr.home.UserInput
import com.example.navigithubpr.utils.NetworkHelper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetPrListUseCase @Inject constructor(
    var prRepository: PrRepository,
    private val networkHelper: NetworkHelper,
): BaseCase<Flow<Resource<List<GithubIssuesResponse>>>, UserInput>()  {

    override suspend fun invoke(userInput: UserInput?)= flow{

        try {

            if(networkHelper.isNetworkConnected()){

                emit(Resource.Loading())
                val lists = prRepository.getTasks(userInput!!).body()
                emit(Resource.Success(lists!!))

            }else{

                emit(Resource.Error(message = "Check your internet connection."))

            }

        } catch(e: HttpException) {

            emit(Resource.Error(message = e.localizedMessage ?: "An unexpected error occured"))

        } catch(e: IOException) {

            emit(Resource.Error(message = "Couldn't reach server. Check your internet connection."))

        }

    }
}