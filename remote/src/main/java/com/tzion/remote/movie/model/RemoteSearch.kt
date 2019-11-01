package com.tzion.remote.movie.model

import com.google.gson.annotations.SerializedName

class RemoteSearch(@SerializedName("Search") val movies: List<RemoteMovie>?,
                   @SerializedName("Response") val response: String?,
                   @SerializedName("Error") val error: String?)