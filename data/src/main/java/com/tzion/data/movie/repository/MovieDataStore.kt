package com.tzion.data.movie.repository

import com.tzion.data.movie.model.DataMovie
import io.reactivex.Single

interface MovieDataStore {

    fun findMoviesByText(text: String?): Single<List<DataMovie>>

}