package com.veselovvv.coinsapp.data.rates

import com.veselovvv.coinsapp.data.rates.cloud.RatesCloudDataSource
import com.veselovvv.coinsapp.data.rates.cloud.RatesCloudMapper

interface RatesRepository {
    suspend fun fetchRates(): RatesData
    suspend fun searchRates(query: String): RatesData

    class Base(
        private val cloudDataSource: RatesCloudDataSource,
        private val cloudMapper: RatesCloudMapper
    ) : RatesRepository {
        override suspend fun fetchRates() = try {
            val ratesCloudList = cloudDataSource.fetchRates()
            val rates = cloudMapper.map(ratesCloudList)
            RatesData.Success(rates)
        } catch (e: Exception) {
            RatesData.Fail(e)
        }

        override suspend fun searchRates(query: String) = try {
            val foundRatesCloudList = cloudDataSource.fetchRates().filter { rate ->
                rate.symbolStartWith(query)
            }

            val rates = cloudMapper.map(foundRatesCloudList)
            RatesData.Success(rates)
        } catch (e: Exception) {
            RatesData.Fail(e)
        }
    }
}