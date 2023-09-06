package com.veselovvv.coinsapp.domain.exchangeinfo

import com.veselovvv.coinsapp.core.Object

data class ExchangeInfoDomain(
    private val percentTotalVolume: String,
    private val volumeUsd: String,
    private val tradingPairs: String,
    private val exchangeUrl: String
) : Object<ExchangeInfoUi, ExchangeInfoDomainToUiMapper> {
    override fun map(mapper: ExchangeInfoDomainToUiMapper) =
        mapper.map(percentTotalVolume, volumeUsd, tradingPairs, exchangeUrl)
}
