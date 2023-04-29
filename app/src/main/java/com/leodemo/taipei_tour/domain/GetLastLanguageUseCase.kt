package com.leodemo.taipei_tour.domain

import com.leodemo.taipei_tour.data.repository.language.AttractionLanguageInteractor
import javax.inject.Inject

class GetLastLanguageUseCase @Inject constructor(
    private val attractionLanguageRepository: AttractionLanguageInteractor
) {
    operator fun invoke(): String {
        return attractionLanguageRepository.getLastLanguage()
    }
}