package com.tzion.data.movie

import com.tzion.data.exception.DataNoMoviesResultsException
import com.tzion.data.movie.mapper.DataMovieMapper
import com.tzion.data.movie.repository.MovieCache
import com.tzion.data.movie.store.MovieDataStoreFactory
import com.tzion.domain.exception.NoMoviesResultsException
import com.tzion.domain.movie.MovieRepository
import com.tzion.domain.movie.model.Movie
import io.reactivex.Single
import javax.inject.Inject

class MovieDataRepository @Inject constructor(
    private val mapper: DataMovieMapper,
    private val cache: MovieCache,
    private val factory: MovieDataStoreFactory)
    : MovieRepository {

    override fun findMoviesByText(text: String?): Single<List<Movie>> = factory
        .getRemoteDataStore()
        .findMoviesByText(text)
        .map { movies ->
            movies.map { dataMovie ->
                with(mapper) { dataMovie.fromDataToDomain() }
            }
        }
        .onErrorResumeNext { error ->
            if (error is DataNoMoviesResultsException) {
                Single.error(NoMoviesResultsException(error.message ?: ""))
            } else {
                Single.error(error)
            }
        }

}