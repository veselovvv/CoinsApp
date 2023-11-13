package com.veselovvv.coinsapp.data.markets.cloud

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

interface MarketsCloudDataSource {
    suspend fun fetchMarkets(): List<MarketCloud>

    class Base(
        private val service: MarketsService,
        private val gson: Gson
    ) : MarketsCloudDataSource {
        private val type = object : TypeToken<MarketsCloud>() {}.type

        override suspend fun fetchMarkets(): List<MarketCloud> {
            val marketsCloud: MarketsCloud = gson.fromJson(service.fetchMarkets().string(), type)
            return marketsCloud.getMarketsList()
        }
    }
}