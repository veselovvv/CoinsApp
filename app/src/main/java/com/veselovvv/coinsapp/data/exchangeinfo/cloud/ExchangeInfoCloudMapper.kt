package com.veselovvv.coinsapp.data.exchangeinfo.cloud

import com.veselovvv.coinsapp.data.exchangeinfo.ExchangeInfoData
import com.veselovvv.coinsapp.data.exchangeinfo.ToExchangeInfoMapper

interface ExchangeInfoCloudMapper {
    fun map(exchangeInfo: ExchangeInfoCloud): ExchangeInfoData

    class Base(private val exchangeInfoMapper: ToExchangeInfoMapper) : ExchangeInfoCloudMapper {
        override fun map(exchangeInfo: ExchangeInfoCloud) = exchangeInfo.map(exchangeInfoMapper)
    }
}