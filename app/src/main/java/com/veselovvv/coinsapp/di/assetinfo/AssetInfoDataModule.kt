package com.veselovvv.coinsapp.di.assetinfo

import com.google.gson.Gson
import com.veselovvv.coinsapp.data.assetinfo.AssetInfoRepository
import com.veselovvv.coinsapp.data.assetinfo.ToAssetInfoMapper
import com.veselovvv.coinsapp.data.assetinfo.cloud.AssetInfoCloudDataSource
import com.veselovvv.coinsapp.data.assetinfo.cloud.AssetInfoCloudMapper
import com.veselovvv.coinsapp.data.assetinfo.cloud.AssetInfoService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AssetInfoDataModule {
    @Provides
    @Singleton
    fun provideAssetInfoService(retrofit: Retrofit): AssetInfoService =
        retrofit.create(AssetInfoService::class.java)

    @Provides
    @Singleton
    fun provideAssetInfoCloudDataSource(
        service: AssetInfoService,
        gson: Gson
    ): AssetInfoCloudDataSource = AssetInfoCloudDataSource.Base(service, gson)

    @Provides
    @Singleton
    fun provideToAssetInfoMapper(): ToAssetInfoMapper = ToAssetInfoMapper.Base()

    @Provides
    @Singleton
    fun provideAssetInfoCloudMapper(assetInfoMapper: ToAssetInfoMapper): AssetInfoCloudMapper =
        AssetInfoCloudMapper.Base(assetInfoMapper)

    @Provides
    @Singleton
    fun provideAssetInfoRepository(
        cloudDataSource: AssetInfoCloudDataSource,
        cloudMapper: AssetInfoCloudMapper
    ): AssetInfoRepository = AssetInfoRepository.Base(cloudDataSource, cloudMapper)
}