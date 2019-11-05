package com.tzion.remote.movie

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.tzion.data.exception.DataNoMoviesResultsException
import com.tzion.data.movie.model.DataMovie
import com.tzion.remote.movie.mapper.RemoteMovieMapper
import com.tzion.remote.movie.model.RemoteMovie
import com.tzion.remote.movie.model.RemoteSearch
import com.tzion.remote.test.factory.RandomDataFactory
import com.tzion.remote.test.factory.MovieDataFactory.makeDataMovie
import com.tzion.remote.test.factory.MovieDataFactory.makeRemoteMovie
import com.tzion.remote.test.factory.MovieDataFactory.makeRemoteSearchAnyErrorMsg
import com.tzion.remote.test.factory.MovieDataFactory.makeRemoteSearchEmptyList
import com.tzion.remote.test.factory.MovieDataFactory.makeRemoteSearchSuccess
import io.mockk.every
import io.mockk.mockk
import io.reactivex.Single
import org.junit.Test

class MovieRemoteImplTest {

    private val mapper = mockk<RemoteMovieMapper>()
    private val restApi = mock<MovieRestApi>()
    private val remote = MovieRemoteImpl(restApi, mapper)

    @Test
    fun `given param, remoteMovie, stubbing restApi and mapper, when remote findMoviesByText, then complete`() {
        val param = RandomDataFactory.generateString()
        val remoteMovie = makeRemoteMovie()
        val dataMovie = makeDataMovie()
        stubRestApiGetMovies(Single.just(makeRemoteSearchSuccess(listOf(remoteMovie))), param)
        stubMapperFromRemoteToData(remoteMovie, dataMovie)

        val testObserver = remote.findMoviesByText(param).test()

        testObserver.assertComplete()
    }

    @Test
    fun `given param and stubbing restApi, when remote findMoviesByText, then verify restApi getMovies`() {
        val param = RandomDataFactory.generateString()
        stubRestApiGetMovies(Single.just(makeRemoteSearchSuccess()), param)

        remote.findMoviesByText(param).test()

        verify(restApi).getMovies(param)
    }

    @Test
    fun `given param, remoteSearch, stubbing restApi and mapper, when remote findMoviesByText, then return data`() {
        val param = RandomDataFactory.generateString()
        val remoteSearch = makeRemoteSearchSuccess()
        stubRestApiGetMovies(Single.just(remoteSearch), param)
        val dataMovies = mutableListOf<DataMovie>()
        remoteSearch.movies!!.forEach {
            val dataMovie = makeDataMovie()
            dataMovies.add(dataMovie)
            stubMapperFromRemoteToData(it, dataMovie)
        }

        val testObserver = remote.findMoviesByText(param).test()

        testObserver.assertValue(dataMovies)
    }

    @Test
    fun `given param, empty remote list with no movies error msg and stubbing restApi, when remote findMoviesByText, then throw DataNoMoviesResultsException`() {
        val param = RandomDataFactory.generateString()
        val remoteSearchEmptyList = makeRemoteSearchEmptyList()
        stubRestApiGetMovies(Single.just(remoteSearchEmptyList), param)

        val testObserver = remote.findMoviesByText(param).test()

        testObserver.assertFailureAndMessage(DataNoMoviesResultsException::class.java, remoteSearchEmptyList.error)
    }

    @Test
    fun `given param, empty remote list with any error msg and stubbing restApi, when remote findMoviesByText, then throw Throwable`() {
        val param = RandomDataFactory.generateString()
        val remoteSearchEmptyList = makeRemoteSearchAnyErrorMsg()
        stubRestApiGetMovies(Single.just(remoteSearchEmptyList), param)

        val testObserver = remote.findMoviesByText(param).test()

        testObserver.assertFailureAndMessage(Throwable::class.java, remoteSearchEmptyList.error)
    }

    private fun stubRestApiGetMovies(single: Single<RemoteSearch>, param: String) {
        whenever(restApi.getMovies(param)).thenReturn(single)
    }

    private fun stubMapperFromRemoteToData(remote: RemoteMovie,
                                           data: DataMovie) {
        with(mapper) {
            every { remote.fromRemoteToData() } returns data
        }
    }

}