package com.tzion.presentation.movie.result

import com.tzion.domain.movie.model.Movie

sealed class MoviesResult {

    sealed class FindMoviesByTextResult: MoviesResult() {
        data class Success(val movies: List<Movie>): FindMoviesByTextResult()
        data class Error(val error: Throwable): FindMoviesByTextResult()
        object InProcess: FindMoviesByTextResult()
    }

}