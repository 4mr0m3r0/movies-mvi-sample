package com.tzion.remote.movie.model

import com.google.gson.annotations.SerializedName
import com.tzion.remote.movie.model.Constants.ERROR
import com.tzion.remote.movie.model.Constants.RESPONSE
import com.tzion.remote.movie.model.Constants.SEARCH

data class RemoteSearch(@SerializedName(SEARCH) val movies: List<RemoteMovie>?,
                        @SerializedName(RESPONSE) val response: String?,
                        @SerializedName(ERROR) val error: String?)