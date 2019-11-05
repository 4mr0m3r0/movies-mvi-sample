package com.tzion.cache.movie.factory

import java.util.*
import java.util.concurrent.ThreadLocalRandom

object RandomDataFactory {

    fun generateString(): String {
        return UUID.randomUUID().toString()
    }

    fun generateInt(): Int {
        return ThreadLocalRandom.current().nextInt(0, 1000 + 1)
    }

    fun generateLong(): Long {
        return generateInt().toLong()
    }

    fun generateBoolean(): Boolean {
        return Math.random() < 0.5
    }

    fun generateDouble(): Double {
        return Math.random()
    }

}