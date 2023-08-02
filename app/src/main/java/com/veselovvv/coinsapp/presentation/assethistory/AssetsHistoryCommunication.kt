package com.veselovvv.coinsapp.presentation.assethistory

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

interface AssetsHistoryCommunication {
    fun map(assetHistory: List<AssetHistoryUi>)
    fun observe(owner: LifecycleOwner, observer: Observer<List<AssetHistoryUi>>)

    class Base : AssetsHistoryCommunication {
        private val assetHistoryLiveData = MutableLiveData<List<AssetHistoryUi>>()

        override fun map(assetHistory: List<AssetHistoryUi>) {
            assetHistoryLiveData.value = assetHistory
        }

        override fun observe(owner: LifecycleOwner, observer: Observer<List<AssetHistoryUi>>) =
            assetHistoryLiveData.observe(owner, observer)
    }
}