package com.veselovvv.coinsapp.di.exchangeinfo

import com.google.gson.Gson
import com.veselovvv.coinsapp.data.exchangeinfo.ExchangeInfoRepository
import com.veselovvv.coinsapp.data.exchangeinfo.ToExchangeInfoMapper
import com.veselovvv.coinsapp.data.exchangeinfo.cloud.ExchangeInfoCloudDataSource
import com.veselovvv.coinsapp.data.exchangeinfo.cloud.ExchangeInfoCloudMapper
import com.veselovvv.coinsapp.data.exchangeinfo.cloud.ExchangeInfoService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ExchangeInfoDataModule {
    @Provides
    @Singleton
    fun provideExchangeInfoService(retrofit: Retrofit): ExchangeInfoService =
        retrofit.create(ExchangeInfoService::class.java)

    @Provides
    @Singleton
    fun provideExchangeInfoCloudDataSource(
        service: ExchangeInfoService,
        gson: Gson
    ): ExchangeInfoCloudDataSource = ExchangeInfoCloudDataSource.Base(service, gson)

    @Provides
    @Singleton
    fun provideToExchangeInfoMapper(): ToExchangeInfoMapper = ToExchangeInfoMapper.Base()

    @Provides
    @Singleton
    fun provideExchangeInfoCloudMapper(
        exchangeInfoMapper: ToExchangeInfoMapper
    ): ExchangeInfoCloudMapper = ExchangeInfoCloudMapper.Base(exchangeInfoMapper)

    @Provides
    @Singleton
    fun provideExchangeInfoRepository(
        cloudDataSource: ExchangeInfoCloudDataSource,
        cloudMapper: ExchangeInfoCloudMapper
    ): ExchangeInfoRepository = ExchangeInfoRepository.Base(cloudDataSource, cloudMapper)
}