package com.veselovvv.coinsapp.domain.rateinfo

class TestRateInfoRepository(private val exception: Exception? = null) : RateInfoRepository {
    override suspend fun fetchRateInfo(id: String) = if (exception == null)
        RatesInfoData.Success(
            RateInfoData(
                currencySymbol = "₿",
                type = "crypto"
            )
        ) else RatesInfoData.Fail(exception)
}