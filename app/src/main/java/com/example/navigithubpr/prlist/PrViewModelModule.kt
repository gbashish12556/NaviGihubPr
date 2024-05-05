package com.example.navigithubpr.prlist

import com.example.navigithubpr.data.source.DefaultPreRepository
import com.example.navigithubpr.data.source.PrRemoteDataSource
import com.example.navigithubpr.data.source.PrRepository
import com.example.navigithubpr.data.source.remote.ApiHelper
import com.example.navigithubpr.data.source.remote.RemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import javax.inject.Qualifier


@Module
@InstallIn(ViewModelComponent::class)
class PrViewModelModule {

    @PrViewModelScope
    @Provides
    fun providePrRepository(
        @PrViewModelScope prRemoteDataSource: PrRemoteDataSource
    ): PrRepository {
        return DefaultPreRepository(prRemoteDataSource);
    }

    @PrViewModelScope
    @Provides
    fun provideRemoteDataSource(apiHelper: ApiHelper): PrRemoteDataSource {
        return RemoteDataSource(apiHelper);
    }


}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class PrViewModelScope
