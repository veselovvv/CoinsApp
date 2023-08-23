package com.veselovvv.coinsapp.presentation.exchanges

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer

class TestExchangesCommunication : ExchangesCommunication {
    private var exchanges = listOf<ExchangeUi>()

    fun getExchanges() = exchanges

    override fun map(exchanges: List<ExchangeUi>) {
        this.exchanges = exchanges
    }

    override fun observe(owner: LifecycleOwner, observer: Observer<List<ExchangeUi>>) = Unit
}