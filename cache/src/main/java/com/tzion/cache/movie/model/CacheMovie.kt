package com.tzion.cache.movie.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = Constants.TABLE_NAME)
data class CacheMovie (
    @PrimaryKey
    @ColumnInfo(name = Constants.COLUMN_MOVIE_ID)
    var id: String,
    @ColumnInfo(name = Constants.COLUMN_TITLE)
    var title: String?,
    @ColumnInfo(name = Constants.COLUMN_YEAR)
    var year: String?,
    @ColumnInfo(name = Constants.COLUMN_RATED)
    var rated: String?,
    @ColumnInfo(name = Constants.COLUMN_RELEASED)
    var released: String?,
    @ColumnInfo(name = Constants.COLUMN_RUNTIME)
    var runtime: String?,
    @ColumnInfo(name = Constants.COLUMN_GENRE)
    var genre: String?,
    @ColumnInfo(name = Constants.COLUMN_DIRECTOR)
    var director: String?,
    @ColumnInfo(name = Constants.COLUMN_WRITER)
    var writer: String?,
    @ColumnInfo(name = Constants.COLUMN_ACTORS)
    var actors: String?,
    @ColumnInfo(name = Constants.COLUMN_PLOT)
    var plot: String?,
    @ColumnInfo(name = Constants.COLUMN_LANGUAGE)
    var language: String?,
    @ColumnInfo(name = Constants.COLUMN_COUNTRY)
    var country: String?,
    @ColumnInfo(name = Constants.COLUMN_AWARDS)
    var awards: String?,
    @ColumnInfo(name = Constants.COLUMN_POSTER)
    var poster: String?,
    @ColumnInfo(name = Constants.COLUMN_METASCORE)
    var metascore: String?,
    @ColumnInfo(name = Constants.COLUMN_IMDBRATING)
    var imdbRating: String?,
    @ColumnInfo(name = Constants.COLUMN_IMDBVOTES)
    var imdbVotes: String?,
    @ColumnInfo(name = Constants.COLUMN_TYPE)
    var type: String?,
    @ColumnInfo(name = Constants.COLUMN_DVD)
    var DVD: String?,
    @ColumnInfo(name = Constants.COLUMN_BOXOFFICE)
    var boxOffice: String?,
    @ColumnInfo(name = Constants.COLUMN_PRODUCTION)
    var production: String?,
    @ColumnInfo(name = Constants.COLUMN_WEBSITE)
    var website: String?
)