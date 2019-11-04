package com.tzion.remote.movie

import com.tzion.data.exception.DataNoMoviesResultsException
import com.tzion.data.movie.model.DataMovie
import com.tzion.data.movie.repository.MovieRemote
import com.tzion.remote.movie.mapper.RemoteMovieMapper
import com.tzion.remote.movie.model.Constants.NO_MOVIES_RESULT_MSG
import io.reactivex.Single
import javax.inject.Inject

class MovieRemoteImpl @Inject constructor(
    private val restApi: MovieRestApi,
    private val mapper: RemoteMovieMapper) : MovieRemote {

    override fun findMoviesByText(text: String?): Single<List<DataMovie>> = restApi
            .getMovies(text)
            .map { searchResult ->
                if (searchResult.movies.isNullOrEmpty()) {
                    if (searchResult.error?.contains(NO_MOVIES_RESULT_MSG) == true) {
                        throw DataNoMoviesResultsException(searchResult.error)
                    } else {
                        throw Throwable(searchResult.error)
                    }
                }
                searchResult.movies.map { movie ->
                    with(mapper) { movie.fromRemoteToData() }
                }
            }

}