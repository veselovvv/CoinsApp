package com.veselovvv.coinsapp.data.rates.cloud

import com.google.gson.annotations.SerializedName
import com.veselovvv.coinsapp.core.Object
import com.veselovvv.coinsapp.data.rates.RateData
import com.veselovvv.coinsapp.data.rates.ToRateMapper

data class RateCloud(
    @SerializedName("id")
    private val id: String,
    @SerializedName("symbol")
    private val symbol: String,
    @SerializedName("rateUsd")
    private val rateUsd: String
) : Object<RateData, ToRateMapper> {
    fun symbolStartWith(query: String) = symbol.startsWith(query)

    override fun map(mapper: ToRateMapper) = mapper.map(id, symbol, rateUsd)
}
