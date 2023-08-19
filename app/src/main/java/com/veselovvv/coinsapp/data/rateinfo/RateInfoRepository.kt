package com.veselovvv.coinsapp.data.rateinfo

import com.veselovvv.coinsapp.data.rateinfo.cloud.RateInfoCloudDataSource
import com.veselovvv.coinsapp.data.rateinfo.cloud.RateInfoCloudMapper

interface RateInfoRepository {
    suspend fun fetchRateInfo(id: String): RatesInfoData

    class Base(
        private val cloudDataSource: RateInfoCloudDataSource,
        private val cloudMapper: RateInfoCloudMapper
    ) : RateInfoRepository {
        override suspend fun fetchRateInfo(id: String) = try {
            val rateInfoCloud = cloudDataSource.fetchRateInfo(id)
            val rateInfoData = cloudMapper.map(rateInfoCloud)
            RatesInfoData.Success(rateInfoData)
        } catch (e: Exception) {
            RatesInfoData.Fail(e)
        }
    }
}