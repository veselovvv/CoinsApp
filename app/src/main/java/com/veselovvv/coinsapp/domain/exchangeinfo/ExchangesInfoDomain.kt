package com.veselovvv.coinsapp.domain.exchangeinfo

import com.veselovvv.coinsapp.core.ErrorType
import com.veselovvv.coinsapp.core.Object
import com.veselovvv.coinsapp.data.exchangeinfo.ExchangeInfoData
import com.veselovvv.coinsapp.data.exchangeinfo.ExchangeInfoDataToDomainMapper
import com.veselovvv.coinsapp.presentation.exchangeinfo.ExchangesInfoUi

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
