package com.veselovvv.coinsapp.domain.exchanges

interface SearchExchangesUseCase {
    suspend fun execute(query: String): ExchangesDomain

    class Base(
        private val repository: ExchangesRepository,
        private val mapper: ExchangesDataToDomainMapper
    ) : SearchExchangesUseCase {
        override suspend fun execute(query: String) = repository.searchExchanges(query).map(mapper)
    }
}