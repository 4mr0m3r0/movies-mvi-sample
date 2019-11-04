package com.tzion.cache.movie.factory

import com.tzion.cache.movie.model.CacheMovie
import com.tzion.data.movie.model.DataMovie

object MovieCacheFactory {

    fun makeCacheMovie() = CacheMovie(
        RandomDataFactory.generateString(),
        RandomDataFactory.generateString(),
        RandomDataFactory.generateString(),
        RandomDataFactory.generateString(),
        RandomDataFactory.generateString(),
        RandomDataFactory.generateString(),
        RandomDataFactory.generateString(),
        RandomDataFactory.generateString(),
        RandomDataFactory.generateString(),
        RandomDataFactory.generateString(),
        RandomDataFactory.generateString(),
        RandomDataFactory.generateString(),
        RandomDataFactory.generateString(),
        RandomDataFactory.generateString(),
        RandomDataFactory.generateString(),
        RandomDataFactory.generateString(),
        RandomDataFactory.generateString(),
        RandomDataFactory.generateString(),
        RandomDataFactory.generateString(),
        RandomDataFactory.generateString(),
        RandomDataFactory.generateString(),
        RandomDataFactory.generateString(),
        RandomDataFactory.generateString()
    )

    fun makeDataMovie() = DataMovie(
        RandomDataFactory.generateString(),
        RandomDataFactory.generateString(),
        RandomDataFactory.generateString(),
        RandomDataFactory.generateString(),
        RandomDataFactory.generateString()
    )

}