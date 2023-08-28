package com.veselovvv.coinsapp.data.exchanges

import com.veselovvv.coinsapp.data.exchanges.cloud.ExchangesCloudDataSource
import com.veselovvv.coinsapp.data.exchanges.cloud.ExchangesCloudMapper

interface ExchangesRepository {
    suspend fun fetchExchanges(): ExchangesData
    suspend fun searchExchanges(query: String): ExchangesData

    class Base(
        private val cloudDataSource: ExchangesCloudDataSource,
        private val cloudMapper: ExchangesCloudMapper
    ) : ExchangesRepository {
        override suspend fun fetchExchanges() = try {
            val exchangesCloudList = cloudDataSource.fetchExchanges()
            val exchanges = cloudMapper.map(exchangesCloudList)
            ExchangesData.Success(exchanges)
        } catch (e: Exception) {
            ExchangesData.Fail(e)
        }

        override suspend fun searchExchanges(query: String) = try {
            val foundExchangesCloudList = cloudDataSource.fetchExchanges().filter { exchange ->
                exchange.nameStartsWith(query)
            }

            val exchanges = cloudMapper.map(foundExchangesCloudList)
            ExchangesData.Success(exchanges)
        } catch (e: Exception) {
            ExchangesData.Fail(e)
        }
    }
}