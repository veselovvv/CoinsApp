package com.veselovvv.coinsapp.data.markets

import com.veselovvv.coinsapp.data.markets.cloud.MarketsCloudDataSource
import com.veselovvv.coinsapp.data.markets.cloud.MarketsCloudMapper

interface MarketsRepository {
    suspend fun fetchMarkets(): MarketsData
    suspend fun searchMarkets(query: String): MarketsData

    class Base(
        private val cloudDataSource: MarketsCloudDataSource,
        private val cloudMapper: MarketsCloudMapper
    ) : MarketsRepository {
        override suspend fun fetchMarkets() = try {
            val marketsCloudList = cloudDataSource.fetchMarkets()
            val markets = cloudMapper.map(marketsCloudList)
            MarketsData.Success(markets)
        } catch (e: Exception) {
            MarketsData.Fail(e)
        }

        override suspend fun searchMarkets(query: String) = try {
            val marketsCloudList = cloudDataSource.fetchMarkets().filter { market ->
                market.exchangeIdStartsWith(query)
            }

            val markets = cloudMapper.map(marketsCloudList)
            MarketsData.Success(markets)
        } catch (e: Exception) {
            MarketsData.Fail(e)
        }
    }
}