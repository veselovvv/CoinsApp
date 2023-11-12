package com.veselovvv.coinsapp.domain.markets

interface FetchMarketsUseCase {
    suspend fun execute(): MarketsDomain

    class Base(
        private val repository: MarketsRepository,
        private val mapper: MarketsDataToDomainMapper
    ) : FetchMarketsUseCase {
        override suspend fun execute() = repository.fetchMarkets().map(mapper)
    }
}