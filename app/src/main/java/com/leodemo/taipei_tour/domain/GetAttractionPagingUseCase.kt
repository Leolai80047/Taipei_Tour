package com.leodemo.taipei_tour.domain

import androidx.paging.PagingData
import com.leodemo.taipei_tour.data.api.AttractionResponse
import com.leodemo.taipei_tour.data.repository.attraction.AttractionInteractor
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAttractionPagingUseCase @Inject constructor(
    private val attractionRepository: AttractionInteractor
) {
    operator fun invoke(): Flow<PagingData<AttractionResponse.Data>> {
        return attractionRepository.getAttractionPagingSource()
    }
}