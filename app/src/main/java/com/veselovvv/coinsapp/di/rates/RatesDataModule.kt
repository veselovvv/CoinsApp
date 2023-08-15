package com.veselovvv.coinsapp.di.rates

import com.google.gson.Gson
import com.veselovvv.coinsapp.data.rates.RatesRepository
import com.veselovvv.coinsapp.data.rates.ToRateMapper
import com.veselovvv.coinsapp.data.rates.cloud.RatesCloudDataSource
import com.veselovvv.coinsapp.data.rates.cloud.RatesCloudMapper
import com.veselovvv.coinsapp.data.rates.cloud.RatesService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RatesDataModule {
    @Provides
    @Singleton
    fun provideRatesService(retrofit: Retrofit): RatesService =
        retrofit.create(RatesService::class.java)

    @Provides
    @Singleton
    fun provideRatesCloudDataSource(
        service: RatesService,
        gson: Gson
    ): RatesCloudDataSource = RatesCloudDataSource.Base(service, gson)

    @Provides
    @Singleton
    fun provideToRateMapper(): ToRateMapper = ToRateMapper.Base()

    @Provides
    @Singleton
    fun provideRatesCloudMapper(rateMapper: ToRateMapper): RatesCloudMapper =
        RatesCloudMapper.Base(rateMapper)

    @Provides
    @Singleton
    fun provideRatesRepository(
        cloudDataSource: RatesCloudDataSource,
        cloudMapper: RatesCloudMapper
    ): RatesRepository = RatesRepository.Base(cloudDataSource, cloudMapper)
}