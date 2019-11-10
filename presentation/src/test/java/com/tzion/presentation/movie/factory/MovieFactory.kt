package com.tzion.presentation.movie.factory

import com.tzion.domain.movie.model.Movie
import com.tzion.presentation.movie.model.PresentationMovie
import com.tzion.presentation.factory.DataFactory

object MovieFactory {

    fun makePresentationMovieList(count: Int): List<PresentationMovie> {
        val movies = mutableListOf<PresentationMovie>()
        repeat(count) {
            movies.add(makePresentationMovie())
        }
        return movies
    }

    fun makeDomainMovieList(count: Int): List<Movie> {
        val movies = mutableListOf<Movie>()
        repeat(count) {
            movies.add(makeDomainMovie())
        }
        return movies
    }

    fun makePresentationMovie(): PresentationMovie {
        return PresentationMovie(
            DataFactory.randomString(),
            DataFactory.randomString(),
            DataFactory.randomString(),
            DataFactory.randomString(),
            DataFactory.randomString()
        )
    }

    fun makeDomainMovie(): Movie {
        return Movie(
            DataFactory.randomString(),
            DataFactory.randomString(),
            DataFactory.randomString(),
            DataFactory.randomString(),
            DataFactory.randomString()
        )
    }

}