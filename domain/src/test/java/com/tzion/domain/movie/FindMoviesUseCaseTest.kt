package com.tzion.domain.movie

import com.nhaarman.mockitokotlin2.whenever
import com.tzion.domain.executor.ExecutionThread
import com.tzion.domain.movie.model.Movie
import com.tzion.domain.test.MovieDataFactory
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FindMoviesUseCaseTest {

    private lateinit var findMoviesUseCase: FindMoviesUseCase
    @Mock lateinit var movieRepository: MovieRepository
    @Mock lateinit var executionThread: ExecutionThread

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        findMoviesUseCase = FindMoviesUseCase(movieRepository)
    }

    @Test
    fun findMoviesUseCaseComplete() {
        val param = MovieDataFactory.randomUuid()
        stubFindMoviesUseCase(Single.just(MovieDataFactory.makeMovieList(2)), param)
        val testObserver = findMoviesUseCase.execute(FindMoviesUseCase.Params(param)).test()
        testObserver.assertComplete()
    }

    @Test
    fun findMoviesUseCaseReturnsData() {
        val param = MovieDataFactory.randomUuid()
        val movies = MovieDataFactory.makeMovieList(2)
        stubFindMoviesUseCase(Single.just(movies), param)
        val testObserver = findMoviesUseCase.execute(FindMoviesUseCase.Params(param)).test()
        testObserver.assertValue(movies)
    }

    private fun stubFindMoviesUseCase(singleObservable: Single<List<Movie>>, param: String?) {
        whenever(movieRepository.findMoviesByText(param)).thenReturn(singleObservable)
    }

}