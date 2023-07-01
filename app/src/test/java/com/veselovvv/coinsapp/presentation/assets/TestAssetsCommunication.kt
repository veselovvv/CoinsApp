package com.veselovvv.coinsapp.presentation.assets

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer

class TestAssetsCommunication : AssetsCommunication {
    private var assets = listOf<AssetUi>()

    fun getAssets() = assets

    override fun map(assets: List<AssetUi>) {
        this.assets = assets
    }

    override fun observe(owner: LifecycleOwner, observer: Observer<List<AssetUi>>) = Unit
}