package com.veselovvv.coinsapp.domain.markets

import com.veselovvv.coinsapp.data.markets.MarketsDataToDomainMapper
import com.veselovvv.coinsapp.data.markets.MarketsRepository

interface FetchMarketsUseCase {
    suspend fun execute(): MarketsDomain

    class Base(
        private val repository: MarketsRepository,
        private val mapper: MarketsDataToDomainMapper
    ) : FetchMarketsUseCase {
        override suspend fun execute() = repository.fetchMarkets().map(mapper)
    }
}