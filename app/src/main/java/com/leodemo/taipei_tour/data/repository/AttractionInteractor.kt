package com.leodemo.taipei_tour.data.repository

import com.leodemo.taipei_tour.data.api.AttractionResponse
import javax.inject.Inject

interface AttractionInteractor  {
    suspend fun fetchAttractions(): List<AttractionResponse.Data>
}