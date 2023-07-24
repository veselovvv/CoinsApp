package com.veselovvv.coinsapp.presentation.assetinfo

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer

class TestAssetsInfoCommunication : AssetsInfoCommunication {
    private var assetInfo: AssetInfoUi = AssetInfoUi.Progress

    fun getAssetInfo() = assetInfo

    override fun map(assetInfo: AssetInfoUi) {
        this.assetInfo = assetInfo
    }

    override fun observe(owner: LifecycleOwner, observer: Observer<AssetInfoUi>) = Unit
}