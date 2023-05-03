package com.leodemo.taipei_tour.domain

import com.leodemo.taipei_tour.data.api.AttractionResponse
import com.leodemo.taipei_tour.data.repository.attraction.AttractionInteractor
import javax.inject.Inject

class FetchAttractionsUseCase @Inject constructor(
    private val attractionRepository: AttractionInteractor
) {
    suspend operator fun invoke(language: String): List<AttractionResponse.Data> {
        return attractionRepository.fetchAttractions(language)
    }
}