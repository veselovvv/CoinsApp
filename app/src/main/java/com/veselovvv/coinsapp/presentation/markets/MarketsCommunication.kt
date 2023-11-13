package com.veselovvv.coinsapp.presentation.markets

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

interface MarketsCommunication {
    fun map(markets: List<MarketUi>)
    fun observe(owner: LifecycleOwner, observer: Observer<List<MarketUi>>)

    class Base : MarketsCommunication {
        private val marketsLiveData = MutableLiveData<List<MarketUi>>()

        override fun map(markets: List<MarketUi>) {
            marketsLiveData.value = markets
        }

        override fun observe(owner: LifecycleOwner, observer: Observer<List<MarketUi>>) =
            marketsLiveData.observe(owner, observer)
    }
}