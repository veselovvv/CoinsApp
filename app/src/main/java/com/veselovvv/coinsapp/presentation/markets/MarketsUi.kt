package com.veselovvv.coinsapp.presentation.markets

import com.veselovvv.coinsapp.core.Object
import com.veselovvv.coinsapp.domain.markets.MarketDomain
import com.veselovvv.coinsapp.domain.markets.MarketDomainToUiMapper

sealed class MarketsUi : Object<Unit, MarketsCommunication> {
    data class Success(
        private val markets: List<MarketDomain>,
        private val marketMapper: MarketDomainToUiMapper
    ) : MarketsUi() {
        override fun map(mapper: MarketsCommunication) {
            if (markets.isEmpty())
                mapper.map(listOf(MarketUi.NoResults))
            else {
                val marketsUi = markets.map { market -> market.map(marketMapper) }
                mapper.map(marketsUi)
            }
        }
    }

    data class Fail(private val errorMessage: String) : MarketsUi() {
        override fun map(mapper: MarketsCommunication) =
            mapper.map(listOf(MarketUi.Fail(errorMessage)))
    }
}
