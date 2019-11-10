package com.tzion.presentation.movie

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.tzion.domain.exception.NoMoviesResultsException
import com.tzion.domain.movie.FindMoviesUseCase
import com.tzion.domain.movie.model.Movie
import com.tzion.presentation.factory.DataFactory
import com.tzion.presentation.movie.factory.MovieFactory.makeDomainMovie
import com.tzion.presentation.movie.factory.MovieFactory.makePresentationMovie
import com.tzion.presentation.movie.intent.MoviesIntent
import com.tzion.presentation.movie.intent.MoviesIntent.*
import com.tzion.presentation.movie.mapper.PresentationMovieMapper
import com.tzion.presentation.movie.model.PresentationMovie
import com.tzion.presentation.movie.processor.MoviesActionProcessor
import com.tzion.presentation.movie.state.MoviesUiState
import io.mockk.every
import io.mockk.mockk
import io.reactivex.Observable
import io.reactivex.Single
import org.junit.Test

class FindMoviesViewModelTest {

    private val useCase = mock<FindMoviesUseCase>()
    private val processor = MoviesActionProcessor(useCase)
    private val mapper = mockk<PresentationMovieMapper>()
    private val viewModel = FindMoviesViewModel(processor, mapper)
    companion object {
        const val POSITION_FOR_DEFAULT = 0
        const val POSITION_FOR_IN_PROGRESS = 1
        const val POSITION_FOR_OTHER_STATES = 2
    }

    @Test
    fun `state Default`() {
        val testObserver = viewModel.states().test()

        testObserver.assertValueAt(POSITION_FOR_DEFAULT) { movieUiState ->
            movieUiState is MoviesUiState.Default
        }
    }

    @Test
    fun `given a list of movies, when process SearchFilterIntent, then state Loading`() {
        val domainMovie = makeDomainMovie()
        val presentationMovie = makePresentationMovie()
        stubPresentationMovieMapper(domainMovie, presentationMovie)
        stubUseCase(Single.just(listOf(domainMovie)))

        val testObserver = viewModel.states().test()
        viewModel.processIntent(Observable.just(SearchFilterIntent(DataFactory.randomString())))

        testObserver.assertValueAt(POSITION_FOR_IN_PROGRESS) { movieUiState ->
            movieUiState is MoviesUiState.Loading
        }
    }

    @Test
    fun `given a list of movies, when process SearchFilterIntent, then state Success`() {
        val domainMovie = makeDomainMovie()
        val presentationMovie = makePresentationMovie()
        stubPresentationMovieMapper(domainMovie, presentationMovie)
        stubUseCase(Single.just(listOf(domainMovie)))

        val testObserver = viewModel.states().test()
        viewModel.processIntent(Observable.just(SearchFilterIntent(DataFactory.randomString())))

        testObserver.assertValueAt(POSITION_FOR_OTHER_STATES) { movieUiState ->
            movieUiState is MoviesUiState.Success
        }
    }

    @Test
    fun `given an empty list of movies, when process SearchFilterIntent, then state EmptyList`() {
        stubUseCase(Single.just(emptyList()))

        val testObserver = viewModel.states().test()
        viewModel.processIntent(Observable.just(SearchFilterIntent(DataFactory.randomString())))

        testObserver.assertValueAt(POSITION_FOR_OTHER_STATES) { movieUiState ->
            movieUiState is MoviesUiState.EmptyList
        }
    }

    @Test
    fun `given NoMoviesResultsException, when process SearchFilterIntent, then state EmptyList`() {
        stubUseCase(Single.error(NoMoviesResultsException(DataFactory.randomString())))

        val testObserver = viewModel.states().test()
        viewModel.processIntent(Observable.just(SearchFilterIntent(DataFactory.randomString())))

        testObserver.assertValueAt(POSITION_FOR_OTHER_STATES) { movieUiState ->
            movieUiState is MoviesUiState.EmptyList
        }
    }

    @Test
    fun `given AnyException, when process SearchFilterIntent, then state EmptyList`() {
        stubUseCase(Single.error(Exception(DataFactory.randomString())))

        val testObserver = viewModel.states().test()
        viewModel.processIntent(Observable.just(SearchFilterIntent(DataFactory.randomString())))

        testObserver.assertValueAt(POSITION_FOR_OTHER_STATES) { movieUiState ->
            movieUiState is MoviesUiState.Error
        }
    }

    private fun stubPresentationMovieMapper(domainMovie: Movie,
                                            presentationMovie: PresentationMovie) {
        with(mapper) {
            every { domainMovie.fromDomainToPresentation() } returns presentationMovie
        }
    }

    private fun stubUseCase(single: Single<List<Movie>>) {
        whenever(useCase.execute(any())).thenReturn(single)
    }

}