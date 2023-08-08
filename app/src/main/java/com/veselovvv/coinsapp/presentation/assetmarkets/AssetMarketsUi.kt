package com.veselovvv.coinsapp.presentation.assetmarkets

import com.veselovvv.coinsapp.core.Object

sealed class AssetMarketsUi : Object<Unit, AssetMarketsUi.BaseMapper> {
    override fun map(mapper: BaseMapper) = Unit

    object Progress : AssetMarketsUi()

    object NoResults : AssetMarketsUi()

    data class Base(
        private val exchangeId: String,
        private val baseId: String,
        private val quoteId: String
    ) : AssetMarketsUi() {
        override fun map(mapper: BaseMapper) = mapper.map(exchangeId, baseId, quoteId)
    }

    data class Fail(private val errorMessage: String) : AssetMarketsUi() {
        override fun map(mapper: BaseMapper) = mapper.map(errorMessage)
    }

    interface BaseMapper {
        fun map(exchangeId: String, baseId: String, quoteId: String)
        fun map(text: String)
    }
}
