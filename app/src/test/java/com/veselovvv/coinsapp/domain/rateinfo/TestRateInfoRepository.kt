package com.veselovvv.coinsapp.domain.rateinfo

import com.veselovvv.coinsapp.data.rateinfo.RateInfoData
import com.veselovvv.coinsapp.data.rateinfo.RateInfoRepository
import com.veselovvv.coinsapp.data.rateinfo.RatesInfoData

class TestRateInfoRepository(private val exception: Exception? = null) : RateInfoRepository {
    override suspend fun fetchRateInfo(id: String) = if (exception == null)
        RatesInfoData.Success(
            RateInfoData(
                currencySymbol = "₿",
                type = "crypto"
            )
        ) else RatesInfoData.Fail(exception)
}