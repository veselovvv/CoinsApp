package com.veselovvv.coinsapp.domain.rates

import com.veselovvv.coinsapp.data.rates.RatesDataToDomainMapper
import com.veselovvv.coinsapp.data.rates.RatesRepository

interface SearchRatesUseCase {
    suspend fun execute(query: String): RatesDomain

    class Base(
        private val repository: RatesRepository,
        private val mapper: RatesDataToDomainMapper
    ) : SearchRatesUseCase {
        override suspend fun execute(query: String) = repository.searchRates(query).map(mapper)
    }
}