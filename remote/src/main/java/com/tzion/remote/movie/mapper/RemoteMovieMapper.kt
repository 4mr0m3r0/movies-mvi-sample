package com.tzion.remote.movie.mapper

import com.tzion.data.movie.model.DataMovie
import com.tzion.remote.movie.model.RemoteMovie
import javax.inject.Inject

class RemoteMovieMapper @Inject constructor() {

    fun RemoteMovie.fromRemoteToData() = DataMovie(
        imdbID = imdbId,
        title = title,
        year = year,
        poster = poster,
        type = type
    )

}