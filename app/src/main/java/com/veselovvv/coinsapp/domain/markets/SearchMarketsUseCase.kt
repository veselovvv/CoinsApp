package com.veselovvv.coinsapp.domain.markets

import com.veselovvv.coinsapp.data.markets.MarketsDataToDomainMapper
import com.veselovvv.coinsapp.data.markets.MarketsRepository

interface SearchMarketsUseCase {
    suspend fun execute(query: String): MarketsDomain

    class Base(
        private val repository: MarketsRepository,
        private val mapper: MarketsDataToDomainMapper
    ) : SearchMarketsUseCase {
        override suspend fun execute(query: String) = repository.searchMarkets(query).map(mapper)
    }
}