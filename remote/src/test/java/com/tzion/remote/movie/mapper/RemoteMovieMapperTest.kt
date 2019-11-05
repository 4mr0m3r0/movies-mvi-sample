package com.tzion.remote.movie.mapper

import com.tzion.remote.test.factory.MovieDataFactory.makeRemoteMovie
import org.junit.Assert.*
import org.junit.Test

class RemoteMovieMapperTest {

    private val mapper = RemoteMovieMapper()

    @Test
    fun `given RemoteMovie, when fromRemoteToData, then return DataMovie`() {
        val remoteMovie = makeRemoteMovie()

        val dataMovie = with(mapper) { remoteMovie.fromRemoteToData() }

        assertEquals("imdbID", remoteMovie.imdbId, dataMovie.imdbID)
        assertEquals("title", remoteMovie.title, dataMovie.title)
        assertEquals("year", remoteMovie.year, dataMovie.year)
        assertEquals("poster", remoteMovie.poster, dataMovie.poster)
        assertEquals("type", remoteMovie.type, dataMovie.type)
    }

}