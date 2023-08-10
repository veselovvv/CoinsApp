package com.veselovvv.coinsapp.domain.rates

interface FetchRatesUseCase {
    suspend fun execute(): RatesDomain

    class Base(
        private val repository: RatesRepository,
        private val mapper: RatesDataToDomainMapper
    ) : FetchRatesUseCase {
        override suspend fun execute() = repository.fetchRates().map(mapper)
    }
}