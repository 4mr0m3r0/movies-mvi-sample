package com.tzion.remote.movie

import com.tzion.data.exception.DataNoMoviesResultsException
import com.tzion.data.movie.model.MovieEntity
import com.tzion.data.movie.repository.MovieRemote
import com.tzion.remote.movie.model.Constants.NO_MOVIES_RESULT_MSG
import io.reactivex.Single
import javax.inject.Inject

class MovieRemoteImpl @Inject constructor(
    private val restApi: MovieRestApi,
    private val mapper: MovieRemoteMapper)
    : MovieRemote {

    override fun findMoviesByText(text: String?): Single<List<MovieEntity>> = restApi
            .getMovies(text)
            .map { searchResult ->
                if (searchResult.movies.isNullOrEmpty()) {
                    if (searchResult.error?.contains(NO_MOVIES_RESULT_MSG) == true) {
                        throw DataNoMoviesResultsException(searchResult.error ?: "")
                    } else {
                        throw Throwable(searchResult.error)
                    }
                }
                searchResult.movies.map { mapper.mapFromRemote(it) }
            }

}