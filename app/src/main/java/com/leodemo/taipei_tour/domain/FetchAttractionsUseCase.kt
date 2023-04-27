package com.leodemo.taipei_tour.domain

import com.leodemo.taipei_tour.data.api.AttractionResponse
import com.leodemo.taipei_tour.data.repository.AttractionInteractor
import com.leodemo.taipei_tour.data.repository.AttractionRepository
import javax.inject.Inject

class FetchAttractionsUseCase @Inject constructor(
    private val attractionRepository: AttractionInteractor
) {
    suspend operator fun invoke(): List<AttractionResponse.Data> {
        return attractionRepository.fetchAttractions()
    }
}