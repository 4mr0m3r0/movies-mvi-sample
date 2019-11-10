package com.tzion.presentation.movie.state

import com.tzion.presentation.movie.model.PresentationMovie

sealed class MoviesUiState(val isLoading: Boolean,
                           val withSearchInstructions: Boolean,
                           val thereAreNotMoviesMatches: Boolean,
                           val movies: List<PresentationMovie>,
                           val withError: Boolean,
                           val errorMessage: String) {
    companion object {
        const val DEFAULT_ERROR_MSG = ""
    }

    object Default: MoviesUiState(
        isLoading = false,
        withSearchInstructions = true,
        thereAreNotMoviesMatches = false,
        movies = emptyList(),
        withError = false,
        errorMessage = DEFAULT_ERROR_MSG
    )

    object Loading: MoviesUiState(
        isLoading = true,
        withSearchInstructions = false,
        thereAreNotMoviesMatches = false,
        movies = emptyList(),
        withError = false,
        errorMessage = DEFAULT_ERROR_MSG
    )

    object EmptyList: MoviesUiState(
        isLoading = false,
        withSearchInstructions = false,
        thereAreNotMoviesMatches = true,
        movies = emptyList(),
        withError = false,
        errorMessage = DEFAULT_ERROR_MSG
    )

    data class Success(private val presentationMovies: List<PresentationMovie>): MoviesUiState(
        isLoading = false,
        withSearchInstructions = false,
        thereAreNotMoviesMatches = false,
        movies = presentationMovies,
        withError = false,
        errorMessage = DEFAULT_ERROR_MSG
    )

    data class Error(private val error: String): MoviesUiState(
        isLoading = false,
        withSearchInstructions = false,
        thereAreNotMoviesMatches = false,
        movies = emptyList(),
        withError = true,
        errorMessage = error
    )

//    companion object {
//        fun default() = MoviesUiState(
//            isLoading = false,
//            withSearchInstructions = true,
//            thereAreNotMoviesMatches = false,
//            movies = emptyList(),
//            withError = false
//        )
//    }

}