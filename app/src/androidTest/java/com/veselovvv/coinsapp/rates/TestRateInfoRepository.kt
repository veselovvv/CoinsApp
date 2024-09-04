package com.veselovvv.coinsapp.rates

import com.veselovvv.coinsapp.data.rateinfo.RateInfoData
import com.veselovvv.coinsapp.data.rateinfo.RateInfoRepository
import com.veselovvv.coinsapp.data.rateinfo.RatesInfoData
import java.net.UnknownHostException

class TestRateInfoRepository : RateInfoRepository {
    private var isSuccess = false

    override suspend fun fetchRateInfo(id: String): RatesInfoData {
        isSuccess = !isSuccess

        return if (isSuccess)
            RatesInfoData.Success(
                RateInfoData(
                    currencySymbol = "B",
                    type = "crypto"
                )
            )
        else
            RatesInfoData.Fail(UnknownHostException())
    }
}