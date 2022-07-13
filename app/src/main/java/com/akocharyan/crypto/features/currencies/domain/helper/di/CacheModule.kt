package com.akocharyan.crypto.features.currencies.domain.helper.di

import android.app.Application
import android.content.ContentResolver
import com.akocharyan.crypto.features.currencies.data.local.CacheRepositoryImpl
import com.akocharyan.crypto.features.currencies.domain.CacheRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CacheModule {

    @Provides
    @Singleton
    fun provideCacheRepository(cacheRepository: CacheRepositoryImpl): CacheRepository = cacheRepository

    @Provides
    @Singleton
    fun provideContentResolver(application: Application): ContentResolver = application.contentResolver
}
