package com.veselovvv.coinsapp.data.rates

interface ToRateMapper {
    fun map(id: String, symbol: String, rateUsd: String): RateData

    class Base : ToRateMapper {
        override fun map(id: String, symbol: String, rateUsd: String) = RateData(id, symbol, rateUsd)
    }
}