package com.veselovvv.coinsapp.domain.rates

import com.veselovvv.coinsapp.data.rates.RateData
import com.veselovvv.coinsapp.data.rates.RatesData
import com.veselovvv.coinsapp.data.rates.RatesRepository

class TestRatesRepository(private val exception: Exception? = null) : RatesRepository {
    private val rates = listOf(
        RateData(id = "barbadian-dollar", symbol = "BBD", rateUsd = "0.5000000000000000"),
        RateData(id = "malawian-kwacha", symbol = "MWK", rateUsd = "0.0013750106599420")
    )

    private val foundRates = listOf(
        RateData(id = "malawian-kwacha", symbol = "MWK", rateUsd = "0.0013750106599420")
    )

    override suspend fun fetchRates() =
        if (exception == null) RatesData.Success(rates) else RatesData.Fail(exception)

    override suspend fun searchRates(query: String) =
        if (exception == null) RatesData.Success(foundRates) else RatesData.Fail(exception)
}