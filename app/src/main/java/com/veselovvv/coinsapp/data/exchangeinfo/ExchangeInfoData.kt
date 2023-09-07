package com.veselovvv.coinsapp.data.exchangeinfo

import com.veselovvv.coinsapp.core.Object
import com.veselovvv.coinsapp.domain.exchangeinfo.ExchangeInfoDomain

data class ExchangeInfoData(
    private val percentTotalVolume: String,
    private val volumeUsd: String,
    private val tradingPairs: String,
    private val exchangeUrl: String
) : Object<ExchangeInfoDomain, ExchangeInfoDataToDomainMapper> {
    override fun map(mapper: ExchangeInfoDataToDomainMapper) =
        mapper.map(percentTotalVolume, volumeUsd, tradingPairs, exchangeUrl)
}
