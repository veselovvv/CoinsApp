package com.veselovvv.coinsapp.presentation.assetmarkets

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer

class TestAssetsMarketsCommunication : AssetsMarketsCommunication {
    private var assetsMarkets = listOf<AssetMarketsUi>()

    fun getAssetsMarkets() = assetsMarkets

    override fun map(assetsMarkets: List<AssetMarketsUi>) {
        this.assetsMarkets = assetsMarkets
    }

    override fun observe(owner: LifecycleOwner, observer: Observer<List<AssetMarketsUi>>) = Unit
}