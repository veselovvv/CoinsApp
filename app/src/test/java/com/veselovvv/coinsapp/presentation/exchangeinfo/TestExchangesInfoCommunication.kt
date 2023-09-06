package com.veselovvv.coinsapp.presentation.exchangeinfo

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer

class TestExchangesInfoCommunication : ExchangesInfoCommunication {
    private var exchangeInfo: ExchangeInfoUi = ExchangeInfoUi.Progress

    fun getExchangeInfo() = exchangeInfo

    override fun map(exchangeInfo: ExchangeInfoUi) {
        this.exchangeInfo = exchangeInfo
    }

    override fun observe(owner: LifecycleOwner, observer: Observer<ExchangeInfoUi>) = Unit
}