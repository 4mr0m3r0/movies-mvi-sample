package com.tzion.presentation.movie.mapper

import com.tzion.domain.movie.model.Movie
import com.tzion.presentation.movie.model.PresentationMovie
import javax.inject.Inject

class PresentationMovieMapper @Inject constructor() {

    fun Movie.fromDomainToPresentation() = PresentationMovie(
        movieId = movieId ?: DEFAULT_VALUE,
        title = title ?: DEFAULT_VALUE,
        year = year ?: DEFAULT_VALUE,
        poster = poster ?: DEFAULT_VALUE,
        type = type ?: DEFAULT_VALUE
    )

    companion object {
        const val DEFAULT_VALUE = ""
    }

}