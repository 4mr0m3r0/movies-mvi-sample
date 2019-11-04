package com.tzion.openmoviesdatabase.movie.mapper

import com.tzion.openmoviesdatabase.movie.factory.MoviesFactory.makePresentationMovie
import org.junit.Assert.*
import org.junit.Test

class UiMovieMapperTest {

    private val mapper = UiMovieMapper()

    @Test
    fun `given PresentationMovie, when fromPresentationToUi, then return UiMovie`() {
        val presentationMovie = makePresentationMovie()

        val uiMovie = with(mapper) { presentationMovie.fromPresentationToUi() }

        assertEquals("movieId", presentationMovie.movieId, uiMovie.movieId)
        assertEquals("title", presentationMovie.title, uiMovie.title)
        assertEquals("year", presentationMovie.year, uiMovie.year)
        assertEquals("poster", presentationMovie.poster, uiMovie.poster)
        assertEquals("type", presentationMovie.type, uiMovie.type)
    }

}