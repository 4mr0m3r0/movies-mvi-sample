package com.tzion.domain.movie

import com.tzion.domain.movie.model.Movie
import io.reactivex.Single

interface MovieRepository {

    fun findMoviesByText(text: String?): Single<List<Movie>>

}