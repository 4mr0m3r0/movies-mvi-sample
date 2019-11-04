package com.tzion.data.movie.store

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.segtechcu.data.test.factory.DataFactory
import com.tzion.data.movie.model.DataMovie
import com.tzion.data.movie.repository.MovieRemote
import com.tzion.data.movie.factory.MovieFactory.makeDataMovie
import io.reactivex.Single
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class MovieRemoteDataStoreTest {

    private val remote = mock<MovieRemote>()
    private val store = MovieRemoteDataStore(remote)

    @Test
    fun findMoviesByTextCompletes() {
        val param = DataFactory.randomString()
        stubRemoteFindMoviesByText(Single.just(listOf(makeDataMovie())), param)
        val testObserver = store.findMoviesByText(param).test()
        testObserver.assertComplete()
    }

    @Test
    fun findMoviesByTextReturnsData() {
        val param = DataFactory.randomString()
        val data = listOf(makeDataMovie())
        stubRemoteFindMoviesByText(Single.just(data), param)
        val testObserver = store.findMoviesByText(param).test()
        testObserver.assertValue(data)
    }

    private fun stubRemoteFindMoviesByText(singleObservable: Single<List<DataMovie>>, param: String?) {
        whenever(remote.findMoviesByText(param)).thenReturn(singleObservable)
    }

}