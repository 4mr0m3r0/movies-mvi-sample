package com.tzion.data.movie.store

import com.tzion.data.movie.model.DataMovie
import com.tzion.data.movie.repository.MovieDataStore
import com.tzion.data.movie.repository.MovieRemote
import io.reactivex.Single
import javax.inject.Inject

class MovieRemoteDataStore @Inject constructor(
    private val movieRemote: MovieRemote)
    : MovieDataStore {

    override fun findMoviesByText(text: String?): Single<List<DataMovie>> {
        return movieRemote.findMoviesByText(text)
    }

}