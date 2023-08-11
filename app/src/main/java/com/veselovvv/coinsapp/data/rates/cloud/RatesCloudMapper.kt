package com.veselovvv.coinsapp.data.rates.cloud

import com.veselovvv.coinsapp.data.rates.RateData
import com.veselovvv.coinsapp.data.rates.ToRateMapper

interface RatesCloudMapper {
    fun map(rates: List<RateCloud>): List<RateData>

    class Base(private val rateMapper: ToRateMapper) : RatesCloudMapper {
        override fun map(rates: List<RateCloud>) = rates.map { rate -> rate.map(rateMapper) }
    }
}