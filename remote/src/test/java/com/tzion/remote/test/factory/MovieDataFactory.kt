package com.tzion.remote.test.factory

import com.tzion.data.movie.model.DataMovie
import com.tzion.remote.movie.model.Constants
import com.tzion.remote.movie.model.RemoteMovie
import com.tzion.remote.movie.model.RemoteSearch


object MovieDataFactory {

    fun makeRemoteSearchSuccess() = RemoteSearch(
        makeListOfMovies(),
        null,
        null
    )

    fun makeRemoteSearchSuccess(movies: List<RemoteMovie>) = RemoteSearch(
        movies,
        null,
        null
    )

    fun makeRemoteSearchEmptyList() = RemoteSearch(
        emptyList(),
        RandomDataFactory.generateString(),
        Constants.NO_MOVIES_RESULT_MSG
    )

    fun makeRemoteSearchAnyErrorMsg() = RemoteSearch(
        emptyList(),
        RandomDataFactory.generateString(),
        RandomDataFactory.generateString()
    )

    fun makeListOfMovies(): List<RemoteMovie> = listOf(makeRemoteMovie(), makeRemoteMovie())

    fun makeRemoteMovie() = RemoteMovie(
        RandomDataFactory.generateString(),
        RandomDataFactory.generateString(),
        RandomDataFactory.generateString(),
        RandomDataFactory.generateString(),
        RandomDataFactory.generateString())


    fun makeDataMovie() = DataMovie(
        RandomDataFactory.generateString(),
        RandomDataFactory.generateString(),
        RandomDataFactory.generateString(),
        RandomDataFactory.generateString(),
        RandomDataFactory.generateString())

}