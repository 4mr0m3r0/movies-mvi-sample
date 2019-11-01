package com.tzion.presentation.movie.intent

sealed class MoviesIntent {

    data class SearchFilterIntent(val queryText: String): MoviesIntent()

}