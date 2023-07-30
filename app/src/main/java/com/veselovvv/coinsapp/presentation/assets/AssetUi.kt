package com.veselovvv.coinsapp.presentation.assets

import com.veselovvv.coinsapp.core.Object

sealed class AssetUi : Object<Unit, AssetUi.BaseMapper> {
    override fun map(mapper: BaseMapper) = Unit
    open fun open(assetListener: AssetsAdapter.AssetListener) = Unit

    object Progress : AssetUi()

    object NoResults : AssetUi()

    data class Base(
        private val id: String,
        private val rank: String,
        private val symbol: String,
        private val name: String
    ) : AssetUi() {
        override fun map(mapper: BaseMapper) = mapper.map(rank, symbol, name)
        override fun open(assetListener: AssetsAdapter.AssetListener) =
            assetListener.showAsset(id, rank, symbol, name)
    }

    data class Fail(private val errorMessage: String) : AssetUi() {
        override fun map(mapper: BaseMapper) = mapper.map(errorMessage)
    }

    interface BaseMapper {
        fun map(rank: String, symbol: String, name: String)
        fun map(text: String)
    }
}
