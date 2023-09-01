package com.veselovvv.coinsapp.data.exchanges.cloud

import com.google.gson.annotations.SerializedName
import com.veselovvv.coinsapp.core.Object
import com.veselovvv.coinsapp.data.exchanges.ExchangeData
import com.veselovvv.coinsapp.data.exchanges.ToExchangeMapper

data class ExchangeCloud(
    @SerializedName("exchangeId")
    private val id: String,
    @SerializedName("name")
    private val name: String,
    @SerializedName("rank")
    private val rank: String
) : Object<ExchangeData, ToExchangeMapper> {
    fun nameStartsWith(query: String) = name.startsWith(query)

    override fun map(mapper: ToExchangeMapper) = mapper.map(id, name, rank)
}
