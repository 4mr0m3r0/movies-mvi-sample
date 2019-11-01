package com.tzion.presentation.movie.action

sealed class MoviesAction {

    data class FindMoviesByTextAction(val queryText: String): MoviesAction()

}