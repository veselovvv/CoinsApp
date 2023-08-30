package com.veselovvv.coinsapp.presentation.exchanges

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

interface ExchangesCommunication {
    fun map(exchanges: List<ExchangeUi>)
    fun observe(owner: LifecycleOwner, observer: Observer<List<ExchangeUi>>)

    class Base : ExchangesCommunication {
        private val exchangesLiveData = MutableLiveData<List<ExchangeUi>>()

        override fun map(exchanges: List<ExchangeUi>) {
            exchangesLiveData.value = exchanges
        }

        override fun observe(owner: LifecycleOwner, observer: Observer<List<ExchangeUi>>) {
            exchangesLiveData.observe(owner, observer)
        }
    }
}