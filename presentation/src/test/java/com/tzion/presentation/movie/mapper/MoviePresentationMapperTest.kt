package com.tzion.presentation.movie.mapper

import com.tzion.presentation.movie.factory.MovieFactory.makeDomainMovie
import org.junit.Assert.*
import org.junit.Test

class MoviePresentationMapperTest {

    private val mapper = PresentationMovieMapper()

    @Test
    fun `given Movie, when fromDomainToPresentation, then return PresentationMovie`() {
        val domainMovie = makeDomainMovie()

        val presentationMovie = with(mapper) { domainMovie.fromDomainToPresentation() }

        assertEquals("movieId", domainMovie.movieId, presentationMovie.movieId)
        assertEquals("title", domainMovie.title, presentationMovie.title)
        assertEquals("year", domainMovie.year, presentationMovie.year)
        assertEquals("poster", domainMovie.poster, presentationMovie.poster)
        assertEquals("type", domainMovie.type, presentationMovie.type)
    }

}