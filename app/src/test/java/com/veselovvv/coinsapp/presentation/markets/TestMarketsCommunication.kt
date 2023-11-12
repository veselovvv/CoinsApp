package com.veselovvv.coinsapp.presentation.markets

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer

class TestMarketsCommunication : MarketsCommunication {
    private var markets = listOf<MarketUi>()

    fun getMarkets() = markets

    override fun map(markets: List<MarketUi>) {
        this.markets = markets
    }

    override fun observe(owner: LifecycleOwner, observer: Observer<List<MarketUi>>) = Unit
}