package com.veselovvv.coinsapp.presentation.assethistory

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer

class TestAssetsHistoryCommunication : AssetsHistoryCommunication {
    private var assetHistory = listOf<AssetHistoryUi>()

    fun getAssetHistory() = assetHistory

    override fun map(assetHistory: List<AssetHistoryUi>) {
        this.assetHistory = assetHistory
    }

    override fun observe(owner: LifecycleOwner, observer: Observer<List<AssetHistoryUi>>) = Unit
}