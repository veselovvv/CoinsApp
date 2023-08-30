package com.veselovvv.coinsapp.domain.exchanges

import com.veselovvv.coinsapp.data.exchanges.ExchangesDataToDomainMapper
import com.veselovvv.coinsapp.data.exchanges.ExchangesRepository

interface FetchExchangesUseCase {
    suspend fun execute(): ExchangesDomain

    class Base(
        private val repository: ExchangesRepository,
        private val mapper: ExchangesDataToDomainMapper
    ) : FetchExchangesUseCase {
        override suspend fun execute() = repository.fetchExchanges().map(mapper)
    }
}