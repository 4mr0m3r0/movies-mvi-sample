package com.tzion.data.movie.mapper

import com.tzion.data.movie.factory.MovieFactory.makeDataMovie
import com.tzion.data.movie.factory.MovieFactory.makeDomainMovie
import org.junit.Assert.*
import org.junit.Test

class DataMovieMapperTest {

    private val mapper = DataMovieMapper()

    @Test
    fun `given DataMovie, when fromDataToDomain, then return Movie`() {
        val dataMovie = makeDataMovie()

        val domainMovie = with(mapper) { dataMovie.fromDataToDomain() }

        assertEquals("movieId", dataMovie.imdbID, domainMovie.movieId)
        assertEquals("title", dataMovie.title, domainMovie.title)
        assertEquals("year", dataMovie.year, domainMovie.year)
        assertEquals("poster", dataMovie.poster, domainMovie.poster)
        assertEquals("type", dataMovie.type, domainMovie.type)
    }

    @Test
    fun `given Movie, when fromDomainToData, then return Movie`() {
        val domainMovie = makeDomainMovie()

        val dataMovie = with(mapper) { domainMovie.fromDomainToData() }

        assertEquals("imdbID", domainMovie.movieId, dataMovie.imdbID)
        assertEquals("title", domainMovie.title, dataMovie.title)
        assertEquals("year", domainMovie.year, dataMovie.year)
        assertEquals("poster", domainMovie.poster, dataMovie.poster)
        assertEquals("type", domainMovie.type, dataMovie.type)
    }

}