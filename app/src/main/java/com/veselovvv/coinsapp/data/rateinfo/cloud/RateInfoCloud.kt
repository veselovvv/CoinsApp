package com.veselovvv.coinsapp.data.rateinfo.cloud

import com.google.gson.annotations.SerializedName
import com.veselovvv.coinsapp.core.Object
import com.veselovvv.coinsapp.data.rateinfo.RateInfoData
import com.veselovvv.coinsapp.data.rateinfo.ToRateInfoMapper

data class RateInfoCloud(
    @SerializedName("currencySymbol")
    private val currencySymbol: String,
    @SerializedName("type")
    private val type: String
) : Object<RateInfoData, ToRateInfoMapper> {
    override fun map(mapper: ToRateInfoMapper) = mapper.map(currencySymbol, type)
}
