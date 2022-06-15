package com.tapmobile.tapyoutube.features.search.di

import com.tapmobile.tapyoutube.features.search.data.YoutubeSearchRepository
import com.tapmobile.tapyoutube.features.search.data.YoutubeSearchRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class SearchModule {

    @Provides
    @Singleton
    fun provideYoutubeSearchRepository(dataSource: YoutubeSearchRepositoryImpl): YoutubeSearchRepository = dataSource
}
