package com.tzion.remote.movie.model

import com.google.gson.annotations.SerializedName
import com.tzion.remote.movie.model.Constants.IMDBID
import com.tzion.remote.movie.model.Constants.POSTER
import com.tzion.remote.movie.model.Constants.TITLE
import com.tzion.remote.movie.model.Constants.TYPE
import com.tzion.remote.movie.model.Constants.YEAR

data class RemoteMovie (@SerializedName(TITLE) val title: String?,
                        @SerializedName(YEAR) val year: String?,
                        @SerializedName(IMDBID) val imdbId: String?,
                        @SerializedName(TYPE) val type: String?,
                        @SerializedName(POSTER) val poster: String?)