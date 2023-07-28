package com.veselovvv.coinsapp.presentation.assetinfo

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

interface AssetsInfoCommunication {
    fun map(assetInfo: AssetInfoUi)
    fun observe(owner: LifecycleOwner, observer: Observer<AssetInfoUi>)

    class Base : AssetsInfoCommunication {
        private val assetInfoLiveData = MutableLiveData<AssetInfoUi>()

        override fun map(assetInfo: AssetInfoUi) {
            assetInfoLiveData.value = assetInfo
        }

        override fun observe(owner: LifecycleOwner, observer: Observer<AssetInfoUi>) =
            assetInfoLiveData.observe(owner, observer)
    }
}