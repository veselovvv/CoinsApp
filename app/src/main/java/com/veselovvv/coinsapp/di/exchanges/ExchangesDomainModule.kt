package com.veselovvv.coinsapp.di.exchanges

import android.content.Context
import com.veselovvv.coinsapp.core.ResourceProvider
import com.veselovvv.coinsapp.core.Save
import com.veselovvv.coinsapp.data.exchanges.ExchangeDataToDomainMapper
import com.veselovvv.coinsapp.data.exchanges.ExchangesDataToDomainMapper
import com.veselovvv.coinsapp.data.exchanges.ExchangesRepository
import com.veselovvv.coinsapp.domain.exchanges.BaseExchangeDataToDomainMapper
import com.veselovvv.coinsapp.domain.exchanges.BaseExchangesDataToDomainMapper
import com.veselovvv.coinsapp.domain.exchanges.ExchangeDomainToUiMapper
import com.veselovvv.coinsapp.domain.exchanges.ExchangesDomainToUiMapper
import com.veselovvv.coinsapp.domain.exchanges.FetchExchangesUseCase
import com.veselovvv.coinsapp.domain.exchanges.SearchExchangesUseCase
import com.veselovvv.coinsapp.presentation.exchanges.BaseExchangeDomainToUiMapper
import com.veselovvv.coinsapp.presentation.exchanges.BaseExchangesDomainToUiMapper
import com.veselovvv.coinsapp.presentation.exchanges.ExchangeCache
import com.veselovvv.coinsapp.presentation.exchanges.ExchangesCommunication
import com.veselovvv.coinsapp.presentation.exchanges.ExchangesParameters
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(ViewModelComponent::class)
class ExchangesDomainModule {
    @Provides
    fun provideExchangesCommunication(): ExchangesCommunication = ExchangesCommunication.Base()

    @Provides
    fun provideExchangeDataToDomainMapper(): ExchangeDataToDomainMapper =
        BaseExchangeDataToDomainMapper()

    @Provides
    fun provideExchangesDataToDomainMapper(
        exchangeMapper: ExchangeDataToDomainMapper
    ): ExchangesDataToDomainMapper = BaseExchangesDataToDomainMapper(exchangeMapper)

    @Provides
    fun provideFetchExchangesUseCase(
        repository: ExchangesRepository,
        exchangesMapper: ExchangesDataToDomainMapper
    ): FetchExchangesUseCase = FetchExchangesUseCase.Base(repository, exchangesMapper)

    @Provides
    fun provideSearchExchangesUseCase(
        repository: ExchangesRepository,
        exchangesMapper: ExchangesDataToDomainMapper
    ): SearchExchangesUseCase = SearchExchangesUseCase.Base(repository, exchangesMapper)

    @Provides
    fun provideExchangeDomainToUiMapper(): ExchangeDomainToUiMapper = BaseExchangeDomainToUiMapper()

    @Provides
    fun provideExchangesDomainToUiMapper(
        resourceProvider: ResourceProvider,
        exchangeMapper: ExchangeDomainToUiMapper
    ): ExchangesDomainToUiMapper = BaseExchangesDomainToUiMapper(resourceProvider, exchangeMapper)

    @Provides
    fun provideExchangeCache(
        @ApplicationContext context: Context
    ): Save<ExchangesParameters> = ExchangeCache.Base(context)
}