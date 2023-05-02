package com.leodemo.taipei_tour.di

import com.leodemo.taipei_tour.data.api.AttractionApi
import com.leodemo.taipei_tour.data.local.sharePreference.ShareLocalDataSource
import com.leodemo.taipei_tour.data.pagingSource.AttractionPagingSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object PagingSourceModule {
    @Provides
    fun provideAttractionPagingSource(
        attractionApi: AttractionApi,
        sharePreferenceDataSource: ShareLocalDataSource
    ): AttractionPagingSource {
        return AttractionPagingSource(
            attractionApi,
            sharePreferenceDataSource
        )
    }
}