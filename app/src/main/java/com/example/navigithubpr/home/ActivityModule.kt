package com.example.navigithubpr.home

import android.app.Activity
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModel
import com.example.kutumbreadsms.data.source.db.RoomDataSource
import com.example.navigithubpr.data.source.DefaultPreRepository
import com.example.navigithubpr.data.source.PrLocalDataSource
import com.example.navigithubpr.data.source.PrRemoteDataSource
import com.example.navigithubpr.data.source.PrRepository
import com.example.navigithubpr.data.source.remote.ApiHelper
import com.example.navigithubpr.data.source.remote.RemoteDataSource
import com.example.truecreditslist.db.PrLocalDb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.scopes.ActivityRetainedScoped
import dagger.hilt.android.scopes.ActivityScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier
import javax.inject.Singleton


@Module
@InstallIn(ViewModelComponent::class)
class ActivityModule {

    @PrViewModeComponent
    @Provides
    fun providePrRepository(@PrViewModeComponent prRemoteDataSource: PrRemoteDataSource,
//                            @PrViewModeComponent prLocalDataSource: PrLocalDataSource
    ): PrRepository {
        return DefaultPreRepository(prRemoteDataSource);
    }

    @PrViewModeComponent
    @Provides
    fun provideRemoteDataSource(apiHelper: ApiHelper):PrRemoteDataSource{
        return RemoteDataSource(apiHelper);
    }


//    @PrViewModeComponent
//    @Provides
//    fun provideLocalDataSource(prLocalDb: PrLocalDb):PrLocalDataSource{
//        return RoomDataSource(prLocalDb.prDao());
//    }

}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class PrViewModeComponent
