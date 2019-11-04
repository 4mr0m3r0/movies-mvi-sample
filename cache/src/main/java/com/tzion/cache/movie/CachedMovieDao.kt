package com.tzion.cache.movie

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tzion.cache.movie.model.Constants.DELETE_MOVIES
import com.tzion.cache.movie.model.Constants.SELECT_MOVIES
import com.tzion.cache.movie.model.CacheMovie
import io.reactivex.Single

@Dao
abstract class CachedMovieDao {

    @Query(SELECT_MOVIES)
    @JvmSuppressWildcards
    abstract fun getMovies(): Single<List<CacheMovie>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @JvmSuppressWildcards
    abstract fun insertMovies(clients: List<CacheMovie>)

    @Query(DELETE_MOVIES)
    abstract fun deleteMovies()

}