package com.tzion.data.movie.factory

import com.segtechcu.data.test.factory.DataFactory
import com.tzion.data.movie.model.DataMovie
import com.tzion.domain.movie.model.Movie

object MovieFactory {

    fun makeDataMovie() = DataMovie(
        DataFactory.randomString(),
        DataFactory.randomString(),
        DataFactory.randomString(),
        DataFactory.randomString(),
        DataFactory.randomString())

    fun makeDomainMovie() = Movie(
        DataFactory.randomString(),
        DataFactory.randomString(),
        DataFactory.randomString(),
        DataFactory.randomString(),
        DataFactory.randomString())

}