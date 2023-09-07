package com.veselovvv.coinsapp.data.exchangeinfo.cloud

import com.google.gson.annotations.SerializedName

data class ExchangesInfoCloud(
    @SerializedName("data")
    private val data: ExchangeInfoCloud
) {
    fun getExchangeInfo() = data
}
