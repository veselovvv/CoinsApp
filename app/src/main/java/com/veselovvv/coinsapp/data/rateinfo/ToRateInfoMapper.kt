package com.veselovvv.coinsapp.data.rateinfo

interface ToRateInfoMapper {
    fun map(currencySymbol: String, type: String): RateInfoData

    class Base : ToRateInfoMapper {
        override fun map(currencySymbol: String, type: String) =
            RateInfoData(currencySymbol, type)
    }
}