package com.tzion.cache.movie

import com.tzion.cache.CacheDatabase
import com.tzion.cache.movie.mapper.CacheMovieMapper
import com.tzion.data.movie.model.DataMovie
import com.tzion.data.movie.repository.MovieCache
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class MovieCacheImpl @Inject constructor(
    private val cacheDatabase: CacheDatabase,
    private val mapper: CacheMovieMapper): MovieCache {

    override fun clearMovies(): Completable = Completable.defer {
        cacheDatabase.cachedMovieDao().deleteMovies()
        Completable.complete()
    }

    override fun saveMovies(clients: List<DataMovie>): Completable = Completable.defer {
        cacheDatabase.cachedMovieDao().insertMovies(
            clients.map { dataMovie ->
                with(mapper) { dataMovie.fromDataToCache() }
            })
        Completable.complete()
    }

    override fun findMoviesByText(text: String?): Single<List<DataMovie>> = cacheDatabase
        .cachedMovieDao()
        .getMovies()
        .map { cacheMovies ->
            with(mapper) { cacheMovies.map { it.fromCacheToData() } }
        }

}