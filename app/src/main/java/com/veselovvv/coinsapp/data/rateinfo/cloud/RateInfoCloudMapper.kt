package com.veselovvv.coinsapp.data.rateinfo.cloud

import com.veselovvv.coinsapp.data.rateinfo.RateInfoData
import com.veselovvv.coinsapp.data.rateinfo.ToRateInfoMapper

interface RateInfoCloudMapper {
    fun map(rateInfo: RateInfoCloud): RateInfoData

    class Base(private val rateInfoMapper: ToRateInfoMapper) : RateInfoCloudMapper {
        override fun map(rateInfo: RateInfoCloud) = rateInfo.map(rateInfoMapper)
    }
}