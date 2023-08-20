package com.veselovvv.coinsapp.di.rateinfo

import android.content.Context
import com.veselovvv.coinsapp.core.Read
import com.veselovvv.coinsapp.core.ResourceProvider
import com.veselovvv.coinsapp.data.rateinfo.RateInfoDataToDomainMapper
import com.veselovvv.coinsapp.data.rateinfo.RateInfoRepository
import com.veselovvv.coinsapp.data.rateinfo.RatesInfoDataToDomainMapper
import com.veselovvv.coinsapp.domain.rateinfo.BaseRateInfoDataToDomainMapper
import com.veselovvv.coinsapp.domain.rateinfo.BaseRatesInfoDataToDomainMapper
import com.veselovvv.coinsapp.domain.rateinfo.FetchRateInfoUseCase
import com.veselovvv.coinsapp.domain.rateinfo.RateInfoDomainToUiMapper
import com.veselovvv.coinsapp.domain.rateinfo.RatesInfoDomainToUiMapper
import com.veselovvv.coinsapp.presentation.rateinfo.BaseRateInfoDomainToUiMapper
import com.veselovvv.coinsapp.presentation.rateinfo.BaseRatesInfoDomainToUiMapper
import com.veselovvv.coinsapp.presentation.rateinfo.RatesInfoCommunication
import com.veselovvv.coinsapp.presentation.rates.RateCache
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(ViewModelComponent::class)
class RateInfoDomainModule {
    @Provides
    fun provideRatesInfoCommunication(): RatesInfoCommunication = RatesInfoCommunication.Base()

    @Provides
    fun provideRateInfoDataToDomainMapper(): RateInfoDataToDomainMapper = BaseRateInfoDataToDomainMapper()

    @Provides
    fun provideRatesInfoDataToDomainMapper(
        rateInfoMapper: RateInfoDataToDomainMapper
    ): RatesInfoDataToDomainMapper = BaseRatesInfoDataToDomainMapper(rateInfoMapper)

    @Provides
    fun provideFetchRateInfoUseCase(
        repository: RateInfoRepository,
        mapper: RatesInfoDataToDomainMapper
    ): FetchRateInfoUseCase = FetchRateInfoUseCase.Base(repository, mapper)

    @Provides
    fun provideRateInfoDomainToUiMapper(): RateInfoDomainToUiMapper = BaseRateInfoDomainToUiMapper()

    @Provides
    fun provideRatesInfoDomainToUiMapper(
        resourceProvider: ResourceProvider,
        rateInfoMapper: RateInfoDomainToUiMapper
    ): RatesInfoDomainToUiMapper = BaseRatesInfoDomainToUiMapper(resourceProvider, rateInfoMapper)

    @Provides
    fun provideRateCache(
        @ApplicationContext context: Context
    ): Read<Triple<String, String, String>> = RateCache.Base(context)
}