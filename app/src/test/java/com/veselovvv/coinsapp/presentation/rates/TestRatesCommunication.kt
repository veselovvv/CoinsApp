package com.veselovvv.coinsapp.presentation.rates

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer

class TestRatesCommunication : RatesCommunication {
    private var rates = listOf<RateUi>()

    fun getRates() = rates

    override fun map(rates: List<RateUi>) {
        this.rates = rates
    }

    override fun observe(owner: LifecycleOwner, observer: Observer<List<RateUi>>) = Unit
}