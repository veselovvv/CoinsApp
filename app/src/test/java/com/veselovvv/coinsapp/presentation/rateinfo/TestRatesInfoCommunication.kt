package com.veselovvv.coinsapp.presentation.rateinfo

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer

class TestRatesInfoCommunication : RatesInfoCommunication {
    private var rateInfo: RateInfoUi = RateInfoUi.Progress

    fun getRateInfo() = rateInfo

    override fun map(rateInfo: RateInfoUi) {
        this.rateInfo = rateInfo
    }

    override fun observe(owner: LifecycleOwner, observer: Observer<RateInfoUi>) = Unit
}