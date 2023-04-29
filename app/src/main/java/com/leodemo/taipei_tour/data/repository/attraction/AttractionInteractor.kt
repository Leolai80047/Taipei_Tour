package com.leodemo.taipei_tour.data.repository.attraction

import com.leodemo.taipei_tour.data.api.AttractionResponse
import javax.inject.Inject

interface AttractionInteractor {
    suspend fun fetchAttractions(language: String): List<AttractionResponse.Data>
}