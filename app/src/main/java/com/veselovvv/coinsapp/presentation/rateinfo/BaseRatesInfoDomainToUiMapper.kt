package com.veselovvv.coinsapp.presentation.rateinfo

import com.veselovvv.coinsapp.R
import com.veselovvv.coinsapp.core.ErrorType
import com.veselovvv.coinsapp.core.ResourceProvider
import com.veselovvv.coinsapp.domain.rateinfo.RateInfoDomain
import com.veselovvv.coinsapp.domain.rateinfo.RateInfoDomainToUiMapper
import com.veselovvv.coinsapp.domain.rateinfo.RatesInfoDomainToUiMapper

class BaseRatesInfoDomainToUiMapper(
    private val resourceProvider: ResourceProvider,
    private val mapper: RateInfoDomainToUiMapper
) : RatesInfoDomainToUiMapper {
    override fun map(rateInfo: RateInfoDomain) = RatesInfoUi.Success(rateInfo, mapper)
    override fun map(errorType: ErrorType) = RatesInfoUi.Fail(
        resourceProvider.getString(
            when (errorType) {
                ErrorType.NO_CONNECTION -> R.string.no_connection_message
                ErrorType.SERVICE_UNAVAILABLE -> R.string.service_unavailable_message
                else -> R.string.something_went_wrong
            }
        )
    )
}