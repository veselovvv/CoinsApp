package com.veselovvv.coinsapp.data.exchangeinfo.cloud

import com.google.gson.annotations.SerializedName
import com.veselovvv.coinsapp.core.Object
import com.veselovvv.coinsapp.data.exchangeinfo.ExchangeInfoData
import com.veselovvv.coinsapp.data.exchangeinfo.ToExchangeInfoMapper

data class ExchangeInfoCloud(
    @SerializedName("percentTotalVolume")
    private val percentTotalVolume: String?,
    @SerializedName("volumeUsd")
    private val volumeUsd: String?,
    @SerializedName("tradingPairs")
    private val tradingPairs: String,
    @SerializedName("exchangeUrl")
    private val exchangeUrl: String
) : Object<ExchangeInfoData, ToExchangeInfoMapper> {
    override fun map(mapper: ToExchangeInfoMapper) =
        mapper.map(
            percentTotalVolume ?: NO_DATA,
            volumeUsd ?: NO_DATA,
            tradingPairs,
            exchangeUrl
        )

    companion object {
        private const val NO_DATA = "-"
    }
}