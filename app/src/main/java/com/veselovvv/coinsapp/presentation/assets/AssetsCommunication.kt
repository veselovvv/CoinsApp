package com.veselovvv.coinsapp.presentation.assets

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.veselovvv.coinsapp.core.Mapper

interface AssetsCommunication : Mapper {
    fun map(assets: List<AssetUi>)
    fun observe(owner: LifecycleOwner, observer: Observer<List<AssetUi>>)

    class Base : AssetsCommunication {
        private val assetsLiveData = MutableLiveData<List<AssetUi>>()

        override fun map(assets: List<AssetUi>) {
            assetsLiveData.value = assets
        }

        override fun observe(owner: LifecycleOwner, observer: Observer<List<AssetUi>>) {
            assetsLiveData.observe(owner, observer)
        }
    }
}