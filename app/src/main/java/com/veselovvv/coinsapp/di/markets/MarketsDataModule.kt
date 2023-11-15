package com.veselovvv.coinsapp.di.markets

import com.google.gson.Gson
import com.veselovvv.coinsapp.data.markets.MarketsRepository
import com.veselovvv.coinsapp.data.markets.ToMarketMapper
import com.veselovvv.coinsapp.data.markets.cloud.MarketsCloudDataSource
import com.veselovvv.coinsapp.data.markets.cloud.MarketsCloudMapper
import com.veselovvv.coinsapp.data.markets.cloud.MarketsService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class MarketsDataModule {
    @Provides
    @Singleton
    fun provideMarketsService(retrofit: Retrofit): MarketsService =
        retrofit.create(MarketsService::class.java)

    @Provides
    @Singleton
    fun provideMarketsCloudDataSource(service: MarketsService, gson: Gson): MarketsCloudDataSource =
        MarketsCloudDataSource.Base(service, gson)

    @Provides
    @Singleton
    fun provideToMarketMapper(): ToMarketMapper = ToMarketMapper.Base()

    @Provides
    @Singleton
    fun provideMarketsCloudMapper(marketMapper: ToMarketMapper): MarketsCloudMapper =
        MarketsCloudMapper.Base(marketMapper)

    @Provides
    @Singleton
    fun provideMarketsRepository(
        cloudDataSource: MarketsCloudDataSource,
        cloudMapper: MarketsCloudMapper
    ): MarketsRepository = MarketsRepository.Base(cloudDataSource, cloudMapper)
}