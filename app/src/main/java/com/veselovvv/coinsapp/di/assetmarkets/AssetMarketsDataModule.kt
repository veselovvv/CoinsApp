package com.veselovvv.coinsapp.di.assetmarkets

import com.google.gson.Gson
import com.veselovvv.coinsapp.data.assetmarkets.AssetMarketsRepository
import com.veselovvv.coinsapp.data.assetmarkets.ToAssetMarketsMapper
import com.veselovvv.coinsapp.data.assetmarkets.cloud.AssetMarketsCloudDataSource
import com.veselovvv.coinsapp.data.assetmarkets.cloud.AssetMarketsCloudMapper
import com.veselovvv.coinsapp.data.assetmarkets.cloud.AssetMarketsService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AssetMarketsDataModule {
    @Provides
    @Singleton
    fun provideAssetMarketsService(retrofit: Retrofit): AssetMarketsService =
        retrofit.create(AssetMarketsService::class.java)

    @Provides
    @Singleton
    fun provideAssetMarketsCloudDataSource(
        service: AssetMarketsService,
        gson: Gson
    ): AssetMarketsCloudDataSource = AssetMarketsCloudDataSource.Base(service, gson)

    @Provides
    @Singleton
    fun provideToAssetMarketsMapper(): ToAssetMarketsMapper = ToAssetMarketsMapper.Base()

    @Provides
    @Singleton
    fun provideAssetMarketsCloudMapper(
        assetMarketsMapper: ToAssetMarketsMapper
    ): AssetMarketsCloudMapper = AssetMarketsCloudMapper.Base(assetMarketsMapper)

    @Provides
    @Singleton
    fun provideAssetMarketsRepository(
        cloudDataSource: AssetMarketsCloudDataSource,
        cloudMapper: AssetMarketsCloudMapper
    ): AssetMarketsRepository = AssetMarketsRepository.Base(cloudDataSource, cloudMapper)
}