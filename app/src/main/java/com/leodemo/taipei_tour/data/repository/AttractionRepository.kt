package com.leodemo.taipei_tour.data.repository

import com.leodemo.taipei_tour.data.api.AttractionApi
import com.leodemo.taipei_tour.data.api.AttractionResponse
import javax.inject.Inject

class AttractionRepository @Inject constructor(
    private val attractionApi: AttractionApi
) : AttractionInteractor {
    override suspend fun fetchAttractions(): List<AttractionResponse.Data> {
        return attractionApi.fetchAttractionList().data
    }
}