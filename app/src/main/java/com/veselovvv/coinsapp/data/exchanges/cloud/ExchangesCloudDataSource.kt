package com.veselovvv.coinsapp.data.exchanges.cloud

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

interface ExchangesCloudDataSource {
    suspend fun fetchExchanges(): List<ExchangeCloud>

    class Base(
        private val service: ExchangesService,
        private val gson: Gson
    ) : ExchangesCloudDataSource {
        private val type = object : TypeToken<ExchangesCloud>() {}.type

        override suspend fun fetchExchanges(): List<ExchangeCloud> {
            val exchangesCloud: ExchangesCloud = gson.fromJson(service.fetchExchanges().string(), type)
            return exchangesCloud.getExchangesList()
        }
    }
}