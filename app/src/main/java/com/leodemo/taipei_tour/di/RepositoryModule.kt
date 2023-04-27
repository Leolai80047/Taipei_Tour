package com.leodemo.taipei_tour.di

import com.leodemo.taipei_tour.data.api.AttractionApi
import com.leodemo.taipei_tour.data.repository.AttractionInteractor
import com.leodemo.taipei_tour.data.repository.AttractionRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideAttractionRepository(attractionApi: AttractionApi): AttractionInteractor {
        return AttractionRepository(attractionApi)
    }

}