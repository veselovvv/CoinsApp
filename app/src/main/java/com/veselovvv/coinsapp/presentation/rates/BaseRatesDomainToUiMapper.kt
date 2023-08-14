package com.veselovvv.coinsapp.presentation.rates

import com.veselovvv.coinsapp.R
import com.veselovvv.coinsapp.core.ErrorType
import com.veselovvv.coinsapp.core.ResourceProvider
import com.veselovvv.coinsapp.domain.rates.RateDomain
import com.veselovvv.coinsapp.domain.rates.RateDomainToUiMapper
import com.veselovvv.coinsapp.domain.rates.RatesDomainToUiMapper

class BaseRatesDomainToUiMapper(
    private val resourceProvider: ResourceProvider,
    private val rateMapper: RateDomainToUiMapper
) : RatesDomainToUiMapper {
    override fun map(rates: List<RateDomain>) = RatesUi.Success(rates, rateMapper)
    override fun map(errorType: ErrorType) = RatesUi.Fail(
        resourceProvider.getString(
            when (errorType) {
                ErrorType.NO_CONNECTION -> R.string.no_connection_message
                ErrorType.SERVICE_UNAVAILABLE -> R.string.service_unavailable_message
                else -> R.string.something_went_wrong
            }
        )
    )
}