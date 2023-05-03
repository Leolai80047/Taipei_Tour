package com.leodemo.taipei_tour.domain

import com.leodemo.taipei_tour.data.repository.language.AttractionLanguageInteractor
import javax.inject.Inject

class SetLastLanguageUseCase @Inject constructor(
    private val attractionLanguageRepository: AttractionLanguageInteractor
) {
    operator fun invoke(value: String) {
        attractionLanguageRepository.setLastLanguage(value)
    }
}