package com.veselovvv.coinsapp.data.rateinfo.cloud

import com.google.gson.annotations.SerializedName

data class RatesInfoCloud(
    @SerializedName("data")
    private val data: RateInfoCloud
) {
    fun getRateInfo() = data
}
