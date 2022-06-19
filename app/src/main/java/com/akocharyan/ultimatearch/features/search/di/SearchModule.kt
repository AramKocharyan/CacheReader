package com.akocharyan.ultimatearch.features.search.di

import com.akocharyan.ultimatearch.features.search.data.YoutubeSearchRepository
import com.akocharyan.ultimatearch.features.search.data.YoutubeSearchRepositoryImpl
import com.akocharyan.ultimatearch.features.search.data.YoutubeSearchService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class SearchModule {

    @Provides
    @Singleton
    fun provideFixtureService(retrofit: Retrofit): YoutubeSearchService = retrofit.create(YoutubeSearchService::class.java)

    @Provides
    @Singleton
    fun provideYoutubeSearchRepository(dataSource: YoutubeSearchRepositoryImpl): YoutubeSearchRepository = dataSource
}
