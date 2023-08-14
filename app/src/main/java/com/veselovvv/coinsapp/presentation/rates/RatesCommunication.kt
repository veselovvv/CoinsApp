package com.veselovvv.coinsapp.presentation.rates

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

interface RatesCommunication {
    fun map(rates: List<RateUi>)
    fun observe(owner: LifecycleOwner, observer: Observer<List<RateUi>>)

    class Base : RatesCommunication {
        private val ratesLiveData = MutableLiveData<List<RateUi>>()

        override fun map(rates: List<RateUi>) {
            ratesLiveData.value = rates
        }

        override fun observe(owner: LifecycleOwner, observer: Observer<List<RateUi>>) =
            ratesLiveData.observe(owner, observer)
    }
}