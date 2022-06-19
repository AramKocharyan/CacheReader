package com.akocharyan.ultimatearch.features.search.di

import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.akocharyan.core.network.PagedData
import com.akocharyan.ultimatearch.features.search.data.YoutubeSearchRepository
import com.akocharyan.ultimatearch.features.search.data.YoutubeSearchRepositoryImpl
import com.akocharyan.ultimatearch.features.search.data.YoutubeSearchService
import com.akocharyan.ultimatearch.features.search.data.YoutubeVideoTypeAdapter
import com.akocharyan.ultimatearch.features.search.domain.models.YoutubeVideoDto
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class YoutubeSearchNetworkModule {

}
