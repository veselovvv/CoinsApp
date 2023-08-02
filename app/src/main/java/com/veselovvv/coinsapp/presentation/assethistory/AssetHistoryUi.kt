package com.veselovvv.coinsapp.presentation.assethistory

import com.veselovvv.coinsapp.core.Object

sealed class AssetHistoryUi : Object<Unit, AssetHistoryUi.BaseMapper> {
    override fun map(mapper: BaseMapper) = Unit

    object Progress : AssetHistoryUi()

    object NoResults : AssetHistoryUi()

    data class Base(
        private val priceUsd: String,
        private val time: String
    ) : AssetHistoryUi() {
        override fun map(mapper: BaseMapper) = mapper.map(priceUsd, time)
    }

    data class Fail(private val errorMessage: String) : AssetHistoryUi() {
        override fun map(mapper: BaseMapper) = mapper.map(errorMessage)
    }

    interface BaseMapper {
        fun map(priceUsd: String, time: String)
        fun map(text: String)
    }
}
