package com.veselovvv.coinsapp.data.rates.cloud

import com.google.gson.annotations.SerializedName

data class RatesCloud(
    @SerializedName("data")
    private val data: List<RateCloud>
) {
    fun getRatesList() = data
}
