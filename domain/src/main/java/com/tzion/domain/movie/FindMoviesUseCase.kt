package com.tzion.domain.movie

import com.tzion.domain.movie.model.Movie
import io.reactivex.Single
import javax.inject.Inject

open class FindMoviesUseCase @Inject constructor(private val movieRepository: MovieRepository) {

    fun execute(params: Params?): Single<List<Movie>> {
        requireNotNull(params) { "Params must not be null" }
        return movieRepository.findMoviesByText(params.text)
    }

    data class Params(val text: String?)

}