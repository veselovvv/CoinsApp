package com.veselovvv.coinsapp.presentation.rateinfo

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

interface RatesInfoCommunication {
    fun map(rateInfo: RateInfoUi)
    fun observe(owner: LifecycleOwner, observer: Observer<RateInfoUi>)

    class Base : RatesInfoCommunication {
        private val rateInfoLiveData = MutableLiveData<RateInfoUi>()

        override fun map(rateInfo: RateInfoUi) {
            rateInfoLiveData.value = rateInfo
        }

        override fun observe(owner: LifecycleOwner, observer: Observer<RateInfoUi>) =
            rateInfoLiveData.observe(owner, observer)
    }
}