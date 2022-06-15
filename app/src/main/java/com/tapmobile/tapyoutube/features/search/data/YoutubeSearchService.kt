package com.tapmobile.tapyoutube.features.search.data

import retrofit2.http.GET
import retrofit2.http.Query

interface YoutubeSearchService {

    @GET("results")
    suspend fun search(
        @Query("search_query") query: String?,
    ): Any

}
