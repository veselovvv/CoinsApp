package com.veselovvv.coinsapp.di.exchanges

import com.google.gson.Gson
import com.veselovvv.coinsapp.data.exchanges.ExchangesRepository
import com.veselovvv.coinsapp.data.exchanges.ToExchangeMapper
import com.veselovvv.coinsapp.data.exchanges.cloud.ExchangesCloudDataSource
import com.veselovvv.coinsapp.data.exchanges.cloud.ExchangesCloudMapper
import com.veselovvv.coinsapp.data.exchanges.cloud.ExchangesService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ExchangesDataModule {
    @Provides
    @Singleton
    fun provideExchangesService(retrofit: Retrofit): ExchangesService =
        retrofit.create(ExchangesService::class.java)

    @Provides
    @Singleton
    fun provideExchangesCloudDataSource(service: ExchangesService, gson: Gson): ExchangesCloudDataSource =
        ExchangesCloudDataSource.Base(service, gson)

    @Provides
    @Singleton
    fun provideToExchangeMapper(): ToExchangeMapper = ToExchangeMapper.Base()

    @Provides
    @Singleton
    fun provideExchangesCloudMapper(exchangeMapper: ToExchangeMapper): ExchangesCloudMapper =
        ExchangesCloudMapper.Base(exchangeMapper)

    @Provides
    @Singleton
    fun provideExchangesRepository(
        cloudDataSource: ExchangesCloudDataSource,
        cloudMapper: ExchangesCloudMapper
    ): ExchangesRepository = ExchangesRepository.Base(cloudDataSource, cloudMapper)
}