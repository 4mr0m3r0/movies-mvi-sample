package com.tzion.data.movie.mapper

import com.tzion.data.movie.model.DataMovie
import com.tzion.domain.movie.model.Movie
import javax.inject.Inject

class DataMovieMapper @Inject constructor() {

    fun DataMovie.fromDataToDomain() = Movie(
        movieId = imdbID,
        title = title,
        year = year,
        poster = poster,
        type = type
    )

    fun Movie.fromDomainToData() = DataMovie(
        imdbID = movieId,
        title = title,
        year = year,
        poster = poster,
        type = type
    )

}