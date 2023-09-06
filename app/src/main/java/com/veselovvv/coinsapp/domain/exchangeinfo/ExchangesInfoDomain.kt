package com.veselovvv.coinsapp.domain.exchangeinfo

import com.veselovvv.coinsapp.core.ErrorType
import com.veselovvv.coinsapp.core.Object

sealed class ExchangesInfoDomain : Object<ExchangesInfoUi, ExchangesInfoDomainToUiMapper> {
    data class Success(
        private val exchangeInfo: ExchangeInfoData,
        private val exchangeInfoMapper: ExchangeInfoDataToDomainMapper
    ) : ExchangesInfoDomain() {
        override fun map(mapper: ExchangesInfoDomainToUiMapper) = mapper.map(
            exchangeInfo.map(exchangeInfoMapper)
        )
    }

    data class Fail(private val error: ErrorType) : ExchangesInfoDomain() {
        override fun map(mapper: ExchangesInfoDomainToUiMapper) = mapper.map(error)
    }
}
