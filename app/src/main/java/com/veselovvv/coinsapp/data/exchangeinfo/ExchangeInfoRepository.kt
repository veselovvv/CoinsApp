package com.veselovvv.coinsapp.data.exchangeinfo

import com.veselovvv.coinsapp.data.exchangeinfo.cloud.ExchangeInfoCloudDataSource
import com.veselovvv.coinsapp.data.exchangeinfo.cloud.ExchangeInfoCloudMapper

interface ExchangeInfoRepository {
    suspend fun fetchExchangeInfo(id: String): ExchangesInfoData

    class Base(
        private val cloudDataSource: ExchangeInfoCloudDataSource,
        private val cloudMapper: ExchangeInfoCloudMapper
    ) : ExchangeInfoRepository {
        override suspend fun fetchExchangeInfo(id: String) = try {
            val exchangeInfoCloud = cloudDataSource.fetchExchangeInfo(id)
            val exchangeInfoData = cloudMapper.map(exchangeInfoCloud)
            ExchangesInfoData.Success(exchangeInfoData)
        } catch (e: Exception) {
            ExchangesInfoData.Fail(e)
        }
    }
}