package com.tzion.openmoviesdatabase.movie.mapper

import com.tzion.openmoviesdatabase.movie.model.UiMovie
import com.tzion.presentation.movie.model.PresentationMovie
import javax.inject.Inject

class UiMovieMapper @Inject constructor() {

    fun PresentationMovie.fromPresentationToUi() = UiMovie(
        movieId = movieId,
        title = title,
        year = year,
        poster = poster,
        type = type
    )

}