package com.leodemo.taipei_tour.data.api

import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface AttractionApi {
    @GET("/open-api/{lang}/Attractions/All")
    suspend fun fetchAttractionList(
        @Header("accept") accept: String = "application/json",
        @Path("lang") lang: String = "en"
    ): AttractionResponse
}