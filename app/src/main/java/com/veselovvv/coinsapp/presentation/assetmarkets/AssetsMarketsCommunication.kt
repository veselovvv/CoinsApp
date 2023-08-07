package com.veselovvv.coinsapp.presentation.assetmarkets

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

interface AssetsMarketsCommunication {
    fun map(assetsMarkets: List<AssetMarketsUi>)
    fun observe(owner: LifecycleOwner, observer: Observer<List<AssetMarketsUi>>)

    class Base : AssetsMarketsCommunication {
        private val assetsMarketsLiveData = MutableLiveData<List<AssetMarketsUi>>()

        override fun map(assetsMarkets: List<AssetMarketsUi>) {
            assetsMarketsLiveData.value = assetsMarkets
        }

        override fun observe(owner: LifecycleOwner, observer: Observer<List<AssetMarketsUi>>) =
            assetsMarketsLiveData.observe(owner, observer)
    }
}