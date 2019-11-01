package com.tzion.presentation.movie.state

import com.tzion.presentation.movie.model.MoviePresentation

sealed class MoviesUiState(val isLoading: Boolean,
                           val withSearchInstructions: Boolean,
                           val thereAreNotMoviesMatches: Boolean,
                           val movies: List<MoviePresentation>,
                           val withError: Boolean,
                           val errorMessage: String) {

    object Default: MoviesUiState(
        isLoading = false,
        withSearchInstructions = true,
        thereAreNotMoviesMatches = false,
        movies = emptyList(),
        withError = false,
        errorMessage = ""
    )

    object Loading: MoviesUiState(
        isLoading = true,
        withSearchInstructions = false,
        thereAreNotMoviesMatches = false,
        movies = emptyList(),
        withError = false,
        errorMessage = ""
    )

    object EmptyList: MoviesUiState(
        isLoading = false,
        withSearchInstructions = false,
        thereAreNotMoviesMatches = true,
        movies = emptyList(),
        withError = false,
        errorMessage = ""
    )

    data class Success(private val presentationMovies: List<MoviePresentation>): MoviesUiState(
        isLoading = false,
        withSearchInstructions = false,
        thereAreNotMoviesMatches = false,
        movies = presentationMovies,
        withError = false,
        errorMessage = ""
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