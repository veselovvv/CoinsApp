package com.veselovvv.coinsapp.presentation.exchangeinfo

import com.veselovvv.coinsapp.core.Object
import com.veselovvv.coinsapp.domain.exchangeinfo.ExchangeInfoDomain
import com.veselovvv.coinsapp.domain.exchangeinfo.ExchangeInfoDomainToUiMapper

sealed class ExchangesInfoUi : Object<Unit, ExchangesInfoCommunication> {
    data class Success(
        private val exchangeInfo: ExchangeInfoDomain,
        private val exchangeInfoMapper: ExchangeInfoDomainToUiMapper
    ) : ExchangesInfoUi() {
        override fun map(mapper: ExchangesInfoCommunication) {
            val exchangeInfoUi = exchangeInfo.map(exchangeInfoMapper)
            mapper.map(exchangeInfoUi)
        }
    }

    data class Fail(private val text: String) : ExchangesInfoUi() {
        override fun map(mapper: ExchangesInfoCommunication) =
            mapper.map(ExchangeInfoUi.Fail(text))
    }
}
