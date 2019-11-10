package com.tzion.presentation.movie.state

import com.tzion.presentation.factory.DataFactory
import com.tzion.presentation.movie.factory.MovieFactory.makePresentationMovieList
import org.junit.Assert.*
import org.junit.Test

class MoviesUiStateTest {

    @Test
    fun `given Default, then assert values`() {
        val default = MoviesUiState.Default

        assertFalse(default.isLoading)
        assertTrue(default.withSearchInstructions)
        assertFalse(default.thereAreNotMoviesMatches)
        assertTrue(default.movies.isEmpty())
        assertFalse(default.withError)
        assertEquals(default.errorMessage, MoviesUiState.DEFAULT_ERROR_MSG)
    }

    @Test
    fun `given Loading, then assert values`() {
        val default = MoviesUiState.Loading

        assertTrue(default.isLoading)
        assertFalse(default.withSearchInstructions)
        assertFalse(default.thereAreNotMoviesMatches)
        assertTrue(default.movies.isEmpty())
        assertFalse(default.withError)
        assertEquals(default.errorMessage, MoviesUiState.DEFAULT_ERROR_MSG)
    }

    @Test
    fun `given EmptyList, then assert values`() {
        val default = MoviesUiState.EmptyList

        assertFalse(default.isLoading)
        assertFalse(default.withSearchInstructions)
        assertTrue(default.thereAreNotMoviesMatches)
        assertTrue(default.movies.isEmpty())
        assertFalse(default.withError)
        assertEquals(default.errorMessage, MoviesUiState.DEFAULT_ERROR_MSG)
    }

    @Test
    fun `given Success, then assert values`() {
        val default = MoviesUiState.Success(makePresentationMovieList(5))

        assertFalse(default.isLoading)
        assertFalse(default.withSearchInstructions)
        assertFalse(default.thereAreNotMoviesMatches)
        assertTrue(default.movies.isNotEmpty())
        assertFalse(default.withError)
        assertEquals(default.errorMessage, MoviesUiState.DEFAULT_ERROR_MSG)
    }

    @Test
    fun `given Error, then assert values`() {
        val errorMsg = DataFactory.randomString()
        val default = MoviesUiState.Error(errorMsg)

        assertFalse(default.isLoading)
        assertFalse(default.withSearchInstructions)
        assertFalse(default.thereAreNotMoviesMatches)
        assertTrue(default.movies.isEmpty())
        assertTrue(default.withError)
        assertEquals(default.errorMessage, errorMsg)
    }

}