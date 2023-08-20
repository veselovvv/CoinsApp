package com.veselovvv.coinsapp.di.rateinfo

import com.google.gson.Gson
import com.veselovvv.coinsapp.data.rateinfo.RateInfoRepository
import com.veselovvv.coinsapp.data.rateinfo.ToRateInfoMapper
import com.veselovvv.coinsapp.data.rateinfo.cloud.RateInfoCloudDataSource
import com.veselovvv.coinsapp.data.rateinfo.cloud.RateInfoCloudMapper
import com.veselovvv.coinsapp.data.rateinfo.cloud.RateInfoService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RateInfoDataModule {
    @Provides
    @Singleton
    fun provideRateInfoService(retrofit: Retrofit): RateInfoService =
        retrofit.create(RateInfoService::class.java)

    @Provides
    @Singleton
    fun provideRateInfoCloudDataSource(
        service: RateInfoService,
        gson: Gson
    ): RateInfoCloudDataSource = RateInfoCloudDataSource.Base(service, gson)

    @Provides
    @Singleton
    fun provideToRateInfoMapper(): ToRateInfoMapper = ToRateInfoMapper.Base()

    @Provides
    @Singleton
    fun provideRateInfoCloudMapper(rateInfoMapper: ToRateInfoMapper): RateInfoCloudMapper =
        RateInfoCloudMapper.Base(rateInfoMapper)

    @Provides
    @Singleton
    fun provideRateInfoRepository(
        cloudDataSource: RateInfoCloudDataSource,
        cloudMapper: RateInfoCloudMapper
    ): RateInfoRepository = RateInfoRepository.Base(cloudDataSource, cloudMapper)
}