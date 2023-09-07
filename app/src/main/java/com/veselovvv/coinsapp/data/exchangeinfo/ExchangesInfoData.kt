package com.veselovvv.coinsapp.data.exchangeinfo

import com.veselovvv.coinsapp.core.Object
import com.veselovvv.coinsapp.domain.exchangeinfo.ExchangesInfoDomain

sealed class ExchangesInfoData : Object<ExchangesInfoDomain, ExchangesInfoDataToDomainMapper> {
    data class Success(private val exchangeInfo: ExchangeInfoData) : ExchangesInfoData() {
        override fun map(mapper: ExchangesInfoDataToDomainMapper) = mapper.map(exchangeInfo)
    }

    data class Fail(private val exception: Exception) : ExchangesInfoData() {
        override fun map(mapper: ExchangesInfoDataToDomainMapper) = mapper.map(exception)
    }
}
