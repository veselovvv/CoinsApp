package com.veselovvv.coinsapp.di.rates

import android.content.Context
import com.veselovvv.coinsapp.core.ResourceProvider
import com.veselovvv.coinsapp.core.Save
import com.veselovvv.coinsapp.data.rates.RateDataToDomainMapper
import com.veselovvv.coinsapp.data.rates.RatesDataToDomainMapper
import com.veselovvv.coinsapp.data.rates.RatesRepository
import com.veselovvv.coinsapp.domain.rates.BaseRateDataToDomainMapper
import com.veselovvv.coinsapp.domain.rates.BaseRatesDataToDomainMapper
import com.veselovvv.coinsapp.domain.rates.FetchRatesUseCase
import com.veselovvv.coinsapp.domain.rates.RateDomainToUiMapper
import com.veselovvv.coinsapp.domain.rates.RatesDomainToUiMapper
import com.veselovvv.coinsapp.domain.rates.SearchRatesUseCase
import com.veselovvv.coinsapp.presentation.rates.BaseRateDomainToUiMapper
import com.veselovvv.coinsapp.presentation.rates.BaseRatesDomainToUiMapper
import com.veselovvv.coinsapp.presentation.rates.RateCache
import com.veselovvv.coinsapp.presentation.rates.RatesCommunication
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(ViewModelComponent::class)
class RatesDomainModule {
    @Provides
    fun provideRatesCommunication(): RatesCommunication = RatesCommunication.Base()

    @Provides
    fun provideRateDataToDomainMapper(): RateDataToDomainMapper = BaseRateDataToDomainMapper()

    @Provides
    fun provideRatesDataToDomainMapper(
        rateMapper: RateDataToDomainMapper
    ): RatesDataToDomainMapper = BaseRatesDataToDomainMapper(rateMapper)

    @Provides
    fun provideFetchRatesUseCase(
        repository: RatesRepository,
        ratesMapper: RatesDataToDomainMapper
    ): FetchRatesUseCase = FetchRatesUseCase.Base(repository, ratesMapper)

    @Provides
    fun provideSearchRatesUseCase(
        repository: RatesRepository,
        ratesMapper: RatesDataToDomainMapper
    ): SearchRatesUseCase = SearchRatesUseCase.Base(repository, ratesMapper)

    @Provides
    fun provideRateDomainToUiMapper(): RateDomainToUiMapper = BaseRateDomainToUiMapper()

    @Provides
    fun provideRatesDomainToUiMapper(
        resourceProvider: ResourceProvider,
        rateMapper: RateDomainToUiMapper
    ): RatesDomainToUiMapper = BaseRatesDomainToUiMapper(resourceProvider, rateMapper)

    @Provides
    fun provideRateCache(
        @ApplicationContext context: Context
    ): Save<Triple<String, String, String>> = RateCache.Base(context)
}