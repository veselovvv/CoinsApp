package com.veselovvv.coinsapp.presentation.exchangeinfo

import com.veselovvv.coinsapp.R
import com.veselovvv.coinsapp.core.ErrorType
import com.veselovvv.coinsapp.core.ResourceProvider
import com.veselovvv.coinsapp.domain.exchangeinfo.ExchangeInfoDomain
import com.veselovvv.coinsapp.domain.exchangeinfo.ExchangeInfoDomainToUiMapper
import com.veselovvv.coinsapp.domain.exchangeinfo.ExchangesInfoDomainToUiMapper

class BaseExchangesInfoDomainToUiMapper(
    private val resourceProvider: ResourceProvider,
    private val mapper: ExchangeInfoDomainToUiMapper
) : ExchangesInfoDomainToUiMapper {
    override fun map(exchangeInfo: ExchangeInfoDomain) = ExchangesInfoUi.Success(exchangeInfo, mapper)
    override fun map(errorType: ErrorType) = ExchangesInfoUi.Fail(
        resourceProvider.getString(
            when (errorType) {
                ErrorType.NO_CONNECTION -> R.string.no_connection_message
                ErrorType.SERVICE_UNAVAILABLE -> R.string.service_unavailable_message
                else -> R.string.something_went_wrong
            }
        )
    )
}