package com.veselovvv.coinsapp.presentation.exchangeinfo

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

interface ExchangesInfoCommunication {
    fun map(exchangeInfo: ExchangeInfoUi)
    fun observe(owner: LifecycleOwner, observer: Observer<ExchangeInfoUi>)

    class Base : ExchangesInfoCommunication {
        private val exchangeInfoLiveData = MutableLiveData<ExchangeInfoUi>()

        override fun map(exchangeInfo: ExchangeInfoUi) {
            exchangeInfoLiveData.value = exchangeInfo
        }

        override fun observe(owner: LifecycleOwner, observer: Observer<ExchangeInfoUi>) =
            exchangeInfoLiveData.observe(owner, observer)
    }
}