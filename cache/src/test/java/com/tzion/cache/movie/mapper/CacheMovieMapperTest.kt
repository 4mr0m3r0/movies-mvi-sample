package com.tzion.cache.movie.mapper

import com.tzion.cache.movie.factory.MovieCacheFactory.makeCacheMovie
import com.tzion.cache.movie.factory.MovieCacheFactory.makeDataMovie
import com.tzion.cache.movie.model.Constants
import org.junit.Assert.*
import org.junit.Test

class CacheMovieMapperTest {

    private val mapper = CacheMovieMapper()

    @Test
    fun `given cacheMovie, when fromCacheToData, then return dataMovie`() {
        val cacheMovie = makeCacheMovie()

        val dataMovie = with(mapper) { cacheMovie.fromCacheToData() }

        assertEquals("imdbID", cacheMovie.id, dataMovie.imdbID)
        assertEquals("title", cacheMovie.title, dataMovie.title)
        assertEquals("year", cacheMovie.year, dataMovie.year)
        assertEquals("poster", cacheMovie.poster, dataMovie.poster)
        assertEquals("type", cacheMovie.type, dataMovie.type)
    }

    @Test
    fun `given dataMovie, when fromCacheToData, then return cacheMovie`() {
        val dataMovie = makeDataMovie()

        val cacheMovie = with(mapper) { dataMovie.fromDataToCache() }

        assertEquals("imdbID", dataMovie.imdbID, cacheMovie.id)
        assertEquals("title", dataMovie.title, cacheMovie.title)
        assertEquals("year", dataMovie.year, cacheMovie.year)
        assertEquals("rated", Constants.DEFAULT_VALUE, cacheMovie.rated)
        assertEquals("released", Constants.DEFAULT_VALUE, cacheMovie.released)
        assertEquals("runtime", Constants.DEFAULT_VALUE, cacheMovie.runtime)
        assertEquals("genre", Constants.DEFAULT_VALUE, cacheMovie.genre)
        assertEquals("director", Constants.DEFAULT_VALUE, cacheMovie.director)
        assertEquals("writer", Constants.DEFAULT_VALUE, cacheMovie.writer)
        assertEquals("actors", Constants.DEFAULT_VALUE, cacheMovie.actors)
        assertEquals("plot", Constants.DEFAULT_VALUE, cacheMovie.plot)
        assertEquals("language", Constants.DEFAULT_VALUE, cacheMovie.language)
        assertEquals("country", Constants.DEFAULT_VALUE, cacheMovie.country)
        assertEquals("awards", Constants.DEFAULT_VALUE, cacheMovie.awards)
        assertEquals("poster", dataMovie.poster, cacheMovie.poster)
        assertEquals("metascore", Constants.DEFAULT_VALUE, cacheMovie.metascore)
        assertEquals("imdbRating", Constants.DEFAULT_VALUE, cacheMovie.imdbRating)
        assertEquals("imdbVotes", Constants.DEFAULT_VALUE, cacheMovie.imdbVotes)
        assertEquals("poster", dataMovie.poster, cacheMovie.poster)
        assertEquals("metascore", Constants.DEFAULT_VALUE, cacheMovie.metascore)
        assertEquals("imdbRating", Constants.DEFAULT_VALUE, cacheMovie.imdbRating)
        assertEquals("imdbVotes", Constants.DEFAULT_VALUE, cacheMovie.imdbVotes)
        assertEquals("type", dataMovie.type, cacheMovie.type)
        assertEquals("DVD", Constants.DEFAULT_VALUE, cacheMovie.DVD)
        assertEquals("boxOffice", Constants.DEFAULT_VALUE, cacheMovie.boxOffice)
        assertEquals("production", Constants.DEFAULT_VALUE, cacheMovie.production)
        assertEquals("website", Constants.DEFAULT_VALUE, cacheMovie.website)
    }

}