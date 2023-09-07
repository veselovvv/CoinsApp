package com.veselovvv.coinsapp.data.exchangeinfo

import com.veselovvv.coinsapp.domain.exchangeinfo.ExchangesInfoDomain

interface ExchangesInfoDataToDomainMapper {
    fun map(exchangeInfo: ExchangeInfoData): ExchangesInfoDomain
    fun map(e: Exception): ExchangesInfoDomain
}