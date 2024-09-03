package com.veselovvv.coinsapp.rates

import com.veselovvv.coinsapp.data.rates.RateData
import com.veselovvv.coinsapp.data.rates.RatesData
import com.veselovvv.coinsapp.data.rates.RatesRepository
import java.net.UnknownHostException

class TestRatesRepository : RatesRepository {
    private var isSuccess = false

    private val rates = listOf(
        RateData(id = "bermudan-dollar", symbol = "BMD", rateUsd = "1.0000000000000000"),
        RateData(id = "norwegian-krone", symbol = "NOK", rateUsd = "0.0944781311469834"),
        RateData(id = "hong-kong-dollar", symbol = "HKD", rateUsd = "0.1282511835339845")
    )

    private val foundRates = listOf(
        RateData(id = "hong-kong-dollar", symbol = "HKD", rateUsd = "0.1282511835339845")
    )

    override suspend fun fetchRates(): RatesData {
        isSuccess = !isSuccess

        return if (isSuccess)
            RatesData.Success(rates)
        else
            RatesData.Fail(UnknownHostException())
    }

    override suspend fun searchRates(query: String) =
        RatesData.Success(
            when {
                query == "" -> rates
                query.startsWith("HK") -> foundRates
                else -> listOf()
            }
        )
}