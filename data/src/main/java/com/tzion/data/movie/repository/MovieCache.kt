package com.tzion.data.movie.repository

import com.tzion.data.movie.model.DataMovie
import io.reactivex.Completable
import io.reactivex.Single

interface MovieCache {

    fun clearMovies(): Completable

    fun saveMovies(clients: List<DataMovie>): Completable

    fun findMoviesByText(text: String?): Single<List<DataMovie>>

}