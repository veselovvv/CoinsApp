package com.veselovvv.coinsapp.presentation.rateinfo

import com.veselovvv.coinsapp.core.Object
import com.veselovvv.coinsapp.domain.rateinfo.RateInfoDomain
import com.veselovvv.coinsapp.domain.rateinfo.RateInfoDomainToUiMapper

sealed class RatesInfoUi : Object<Unit, RatesInfoCommunication> {
    data class Success(
        private val rateInfo: RateInfoDomain,
        private val rateInfoMapper: RateInfoDomainToUiMapper
    ) : RatesInfoUi() {
        override fun map(mapper: RatesInfoCommunication) {
            val rateInfoUi = rateInfo.map(rateInfoMapper)
            mapper.map(rateInfoUi)
        }
    }

    data class Fail(private val text: String) : RatesInfoUi() {
        override fun map(mapper: RatesInfoCommunication) = mapper.map(RateInfoUi.Fail(text))
    }
}
