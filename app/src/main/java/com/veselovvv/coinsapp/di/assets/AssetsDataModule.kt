package com.veselovvv.coinsapp.di.assets

import com.google.gson.Gson
import com.veselovvv.coinsapp.data.assets.AssetsRepository
import com.veselovvv.coinsapp.data.assets.ToAssetMapper
import com.veselovvv.coinsapp.data.assets.cloud.AssetsCloudDataSource
import com.veselovvv.coinsapp.data.assets.cloud.AssetsCloudMapper
import com.veselovvv.coinsapp.data.assets.cloud.AssetsService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AssetsDataModule {
    @Provides
    @Singleton
    fun provideAssetsService(retrofit: Retrofit): AssetsService =
        retrofit.create(AssetsService::class.java)

    @Provides
    @Singleton
    fun provideAssetsCloudDataSource(service: AssetsService, gson: Gson): AssetsCloudDataSource =
        AssetsCloudDataSource.Base(service, gson)

    @Provides
    @Singleton
    fun provideToAssetMapper(): ToAssetMapper = ToAssetMapper.Base()

    @Provides
    @Singleton
    fun provideAssetsRepository(
        cloudDataSource: AssetsCloudDataSource,
        cloudMapper: AssetsCloudMapper
    ): AssetsRepository = AssetsRepository.Base(cloudDataSource, cloudMapper)
}