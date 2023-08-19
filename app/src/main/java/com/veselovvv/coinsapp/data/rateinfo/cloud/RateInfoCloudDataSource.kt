package com.veselovvv.coinsapp.data.rateinfo.cloud

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

interface RateInfoCloudDataSource {
    suspend fun fetchRateInfo(id: String): RateInfoCloud

    class Base(
        private val service: RateInfoService,
        private val gson: Gson
    ) : RateInfoCloudDataSource {
        private val type = object : TypeToken<RatesInfoCloud>() {}.type

        override suspend fun fetchRateInfo(id: String): RateInfoCloud {
            val ratesInfoCloud: RatesInfoCloud = gson.fromJson(service.fetchRateInfo(id).string(), type)
            return ratesInfoCloud.getRateInfo()
        }
    }
}