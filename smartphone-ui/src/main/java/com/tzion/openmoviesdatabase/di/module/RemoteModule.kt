package com.tzion.openmoviesdatabase.di.module

import com.tzion.data.movie.repository.MovieRemote
import com.tzion.remote.EndpointConstants
import com.tzion.remote.RemoteApiRestFactory
import com.tzion.remote.movie.MovieRemoteImpl
import com.tzion.remote.movie.MovieRestApi
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class RemoteModule {
    @Module
    companion object {
        @Provides
        @JvmStatic
        fun provideMovieRestApi(): MovieRestApi {
            return RemoteApiRestFactory<MovieRestApi>().makeRemoteRestApi(
                    true, MovieRestApi::class.java, EndpointConstants.REST_API_SERVER
            )
        }
    }

    @Binds
    abstract fun bindMoviesRemote(movieRemoteImpl: MovieRemoteImpl): MovieRemote
}