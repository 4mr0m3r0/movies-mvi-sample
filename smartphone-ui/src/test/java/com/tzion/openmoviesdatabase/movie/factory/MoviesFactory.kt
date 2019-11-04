package com.tzion.openmoviesdatabase.movie.factory

import com.tzion.domain.movie.model.Movie
import com.tzion.openmoviesdatabase.factory.RandomFactory
import com.tzion.openmoviesdatabase.movie.model.UiMovie
import com.tzion.presentation.movie.model.PresentationMovie

object MoviesFactory {

    fun makePresentationMovieList(count: Int): List<PresentationMovie> {
        return (0..count).map { makePresentationMovie() }
    }

    fun makePresentationMovie(): PresentationMovie {
        return PresentationMovie(
            RandomFactory.generateString(),
            RandomFactory.generateString(),
            RandomFactory.generateString(),
            RandomFactory.generateString(),
            RandomFactory.generateString()
        )
    }

    fun makeUiMovieList(count: Int): List<UiMovie> {
        return (0..count).map { makeUiMovie() }
    }

    fun makeUiMovie(): UiMovie {
        return UiMovie(
            RandomFactory.generateString(),
            RandomFactory.generateString(),
            RandomFactory.generateString(),
            "https://picsum.photos/200/300/?random",
            RandomFactory.generateString()
        )
    }

}