package com.tzion.cache.movie.mapper

import com.tzion.cache.movie.model.CacheMovie
import com.tzion.cache.movie.model.Constants.DEFAULT_VALUE
import com.tzion.data.movie.model.DataMovie
import java.util.*
import javax.inject.Inject

class CacheMovieMapper @Inject constructor() {

    fun DataMovie.fromDataToCache() = CacheMovie(
        id = imdbID ?: UUID.randomUUID().toString(),
        title = title,
        year = year,
        rated = DEFAULT_VALUE,
        released = DEFAULT_VALUE,
        runtime = DEFAULT_VALUE,
        genre = DEFAULT_VALUE,
        director = DEFAULT_VALUE,
        writer = DEFAULT_VALUE,
        actors = DEFAULT_VALUE,
        plot = DEFAULT_VALUE,
        language = DEFAULT_VALUE,
        country = DEFAULT_VALUE,
        awards = DEFAULT_VALUE,
        poster = poster,
        metascore = DEFAULT_VALUE,
        imdbRating = DEFAULT_VALUE,
        imdbVotes = DEFAULT_VALUE,
        type = type,
        DVD = DEFAULT_VALUE,
        boxOffice = DEFAULT_VALUE,
        production = DEFAULT_VALUE,
        website = DEFAULT_VALUE
    )

    fun CacheMovie.fromCacheToData() = DataMovie(
        imdbID = id,
        title = title,
        year = year,
        poster = poster,
        type = type
    )

}