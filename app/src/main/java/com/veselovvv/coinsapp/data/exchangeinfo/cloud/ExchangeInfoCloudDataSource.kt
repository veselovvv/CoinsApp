package com.veselovvv.coinsapp.data.exchangeinfo.cloud

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

interface ExchangeInfoCloudDataSource {
    suspend fun fetchExchangeInfo(id: String): ExchangeInfoCloud

    class Base(
        private val service: ExchangeInfoService,
        private val gson: Gson
    ) : ExchangeInfoCloudDataSource {
        private val type = object : TypeToken<ExchangesInfoCloud>() {}.type

        override suspend fun fetchExchangeInfo(id: String): ExchangeInfoCloud {
            val exchangesInfoCloud: ExchangesInfoCloud =
                gson.fromJson(service.fetchExchangeInfo(id).string(), type)
            return exchangesInfoCloud.getExchangeInfo()
        }
    }
}