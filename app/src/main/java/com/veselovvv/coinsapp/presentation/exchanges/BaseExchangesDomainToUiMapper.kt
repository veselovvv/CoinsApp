package com.veselovvv.coinsapp.presentation.exchanges

import com.veselovvv.coinsapp.R
import com.veselovvv.coinsapp.core.ErrorType
import com.veselovvv.coinsapp.core.ResourceProvider
import com.veselovvv.coinsapp.domain.exchanges.ExchangeDomain
import com.veselovvv.coinsapp.domain.exchanges.ExchangeDomainToUiMapper
import com.veselovvv.coinsapp.domain.exchanges.ExchangesDomainToUiMapper

class BaseExchangesDomainToUiMapper(
    private val resourceProvider: ResourceProvider,
    private val exchangeMapper: ExchangeDomainToUiMapper
) : ExchangesDomainToUiMapper {
    override fun map(exchanges: List<ExchangeDomain>) = ExchangesUi.Success(exchanges, exchangeMapper)
    override fun map(errorType: ErrorType) = ExchangesUi.Fail(
        resourceProvider.getString(
            when (errorType) {
                ErrorType.NO_CONNECTION -> R.string.no_connection_message
                ErrorType.SERVICE_UNAVAILABLE -> R.string.service_unavailable_message
                else -> R.string.something_went_wrong
            }
        )
    )
}