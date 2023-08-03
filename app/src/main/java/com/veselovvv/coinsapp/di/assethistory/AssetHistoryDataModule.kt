package com.veselovvv.coinsapp.di.assethistory

import com.google.gson.Gson
import com.veselovvv.coinsapp.data.assethistory.AssetHistoryRepository
import com.veselovvv.coinsapp.data.assethistory.ToAssetHistoryMapper
import com.veselovvv.coinsapp.data.assethistory.cloud.AssetHistoryCloudDataSource
import com.veselovvv.coinsapp.data.assethistory.cloud.AssetHistoryCloudMapper
import com.veselovvv.coinsapp.data.assethistory.cloud.AssetHistoryService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AssetHistoryDataModule {
    @Provides
    @Singleton
    fun provideAssetHistoryService(retrofit: Retrofit): AssetHistoryService =
        retrofit.create(AssetHistoryService::class.java)

    @Provides
    @Singleton
    fun provideAssetHistoryCloudDataSource(
        service: AssetHistoryService,
        gson: Gson
    ): AssetHistoryCloudDataSource = AssetHistoryCloudDataSource.Base(service, gson)

    @Provides
    @Singleton
    fun provideToAssetHistoryMapper(): ToAssetHistoryMapper = ToAssetHistoryMapper.Base()

    @Provides
    @Singleton
    fun provideAssetHistoryCloudMapper(
        assetHistoryMapper: ToAssetHistoryMapper
    ): AssetHistoryCloudMapper = AssetHistoryCloudMapper.Base(assetHistoryMapper)

    @Provides
    @Singleton
    fun provideAssetHistoryRepository(
        cloudDataSource: AssetHistoryCloudDataSource,
        cloudMapper: AssetHistoryCloudMapper
    ): AssetHistoryRepository = AssetHistoryRepository.Base(cloudDataSource, cloudMapper)
}