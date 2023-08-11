package com.veselovvv.coinsapp.data.rates.cloud

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

interface RatesCloudDataSource {
    suspend fun fetchRates(): List<RateCloud>

    class Base(
        private val service: RatesService,
        private val gson: Gson
    ) : RatesCloudDataSource {
        private val type = object : TypeToken<RatesCloud>() {}.type

        override suspend fun fetchRates(): List<RateCloud> {
            val ratesCloud: RatesCloud = gson.fromJson(service.fetchRates().string(), type)
            return ratesCloud.getRatesList()
        }
    }
}