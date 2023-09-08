package com.veselovvv.coinsapp.presentation.exchangeinfo

import com.veselovvv.coinsapp.domain.exchangeinfo.ExchangeInfoDomainToUiMapper

class BaseExchangeInfoDomainToUiMapper : ExchangeInfoDomainToUiMapper {
    override fun map(
        percentTotalVolume: String,
        volumeUsd: String,
        tradingPairs: String,
        exchangeUrl: String
    ) = ExchangeInfoUi.Base(percentTotalVolume, volumeUsd, tradingPairs, exchangeUrl)
}