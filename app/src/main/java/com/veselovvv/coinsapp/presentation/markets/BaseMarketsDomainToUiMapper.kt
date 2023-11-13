package com.veselovvv.coinsapp.presentation.markets

import com.veselovvv.coinsapp.R
import com.veselovvv.coinsapp.core.ErrorType
import com.veselovvv.coinsapp.core.ResourceProvider
import com.veselovvv.coinsapp.domain.markets.MarketDomain
import com.veselovvv.coinsapp.domain.markets.MarketDomainToUiMapper
import com.veselovvv.coinsapp.domain.markets.MarketsDomainToUiMapper

class BaseMarketsDomainToUiMapper(
    private val resourceProvider: ResourceProvider,
    private val marketMapper: MarketDomainToUiMapper
) : MarketsDomainToUiMapper {
    override fun map(markets: List<MarketDomain>) = MarketsUi.Success(markets, marketMapper)
    override fun map(errorType: ErrorType) = MarketsUi.Fail(
        resourceProvider.getString(
            when (errorType) {
                ErrorType.NO_CONNECTION -> R.string.no_connection_message
                ErrorType.SERVICE_UNAVAILABLE -> R.string.service_unavailable_message
                else -> R.string.something_went_wrong
            }
        )
    )
}