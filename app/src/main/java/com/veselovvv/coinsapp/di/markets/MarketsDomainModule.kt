package com.veselovvv.coinsapp.di.markets

import com.veselovvv.coinsapp.core.ResourceProvider
import com.veselovvv.coinsapp.data.markets.MarketDataToDomainMapper
import com.veselovvv.coinsapp.data.markets.MarketsDataToDomainMapper
import com.veselovvv.coinsapp.data.markets.MarketsRepository
import com.veselovvv.coinsapp.domain.markets.BaseMarketDataToDomainMapper
import com.veselovvv.coinsapp.domain.markets.BaseMarketsDataToDomainMapper
import com.veselovvv.coinsapp.domain.markets.FetchMarketsUseCase
import com.veselovvv.coinsapp.domain.markets.MarketDomainToUiMapper
import com.veselovvv.coinsapp.domain.markets.MarketsDomainToUiMapper
import com.veselovvv.coinsapp.domain.markets.SearchMarketsUseCase
import com.veselovvv.coinsapp.presentation.markets.BaseMarketDomainToUiMapper
import com.veselovvv.coinsapp.presentation.markets.BaseMarketsDomainToUiMapper
import com.veselovvv.coinsapp.presentation.markets.MarketsCommunication
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class MarketsDomainModule {
    @Provides
    fun provideMarketsCommunication(): MarketsCommunication = MarketsCommunication.Base()

    @Provides
    fun provideMarketDataToDomainMapper(): MarketDataToDomainMapper = BaseMarketDataToDomainMapper()

    @Provides
    fun provideMarketsDataToDomainMapper(
        marketMapper: MarketDataToDomainMapper
    ): MarketsDataToDomainMapper = BaseMarketsDataToDomainMapper(marketMapper)

    @Provides
    fun provideFetchMarketsUseCase(
        repository: MarketsRepository,
        mapper: MarketsDataToDomainMapper
    ): FetchMarketsUseCase = FetchMarketsUseCase.Base(repository, mapper)

    @Provides
    fun provideSearchMarketsUseCase(
        repository: MarketsRepository,
        mapper: MarketsDataToDomainMapper
    ): SearchMarketsUseCase = SearchMarketsUseCase.Base(repository, mapper)

    @Provides
    fun provideMarketDomainToUiMapper(): MarketDomainToUiMapper = BaseMarketDomainToUiMapper()

    @Provides
    fun provideMarketsDomainToUiMapper(
        resourceProvider: ResourceProvider,
        marketMapper: MarketDomainToUiMapper
    ): MarketsDomainToUiMapper = BaseMarketsDomainToUiMapper(resourceProvider, marketMapper)
}