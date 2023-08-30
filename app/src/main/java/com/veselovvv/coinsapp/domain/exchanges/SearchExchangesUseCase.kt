package com.veselovvv.coinsapp.domain.exchanges

import com.veselovvv.coinsapp.data.exchanges.ExchangesDataToDomainMapper
import com.veselovvv.coinsapp.data.exchanges.ExchangesRepository

interface SearchExchangesUseCase {
    suspend fun execute(query: String): ExchangesDomain

    class Base(
        private val repository: ExchangesRepository,
        private val mapper: ExchangesDataToDomainMapper
    ) : SearchExchangesUseCase {
        override suspend fun execute(query: String) = repository.searchExchanges(query).map(mapper)
    }
}