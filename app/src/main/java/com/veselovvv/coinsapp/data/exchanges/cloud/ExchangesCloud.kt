package com.veselovvv.coinsapp.data.exchanges.cloud

import com.google.gson.annotations.SerializedName

data class ExchangesCloud(
    @SerializedName("data")
    private val data: List<ExchangeCloud>
) {
    fun getExchangesList() = data
}
