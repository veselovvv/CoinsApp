package com.veselovvv.coinsapp.presentation.assetmarkets

import android.content.Context
import com.veselovvv.coinsapp.core.Read
import com.veselovvv.coinsapp.core.Save

interface AssetMarketsCache : Save<Triple<String, String, String>>, Read<Triple<String, String, String>> {
    class Base(context: Context) : AssetMarketsCache {
        private val sharedPreferences =
            context.getSharedPreferences(ASSET_MARKETS_DATA_FILENAME, Context.MODE_PRIVATE)

        override fun save(data: Triple<String, String, String>) =
            sharedPreferences.edit()
                .putString(ASSET_MARKETS_EXCHANGE_ID_KEY, data.first)
                .putString(ASSET_MARKETS_BASE_ID_KEY, data.second)
                .putString(ASSET_MARKETS_QUOTE_ID_KEY, data.third)
                .apply()

        override fun read() = Triple(
            sharedPreferences.getString(ASSET_MARKETS_EXCHANGE_ID_KEY, "") ?: "",
            sharedPreferences.getString(ASSET_MARKETS_BASE_ID_KEY, "") ?: "",
            sharedPreferences.getString(ASSET_MARKETS_QUOTE_ID_KEY, "") ?: ""
        )

        companion object {
            private const val ASSET_MARKETS_DATA_FILENAME = "assetMarketsData"
            private const val ASSET_MARKETS_EXCHANGE_ID_KEY = "assetMarketsExchangeIdKey"
            private const val ASSET_MARKETS_BASE_ID_KEY = "assetMarketsBaseIdKey"
            private const val ASSET_MARKETS_QUOTE_ID_KEY = "assetMarketsQuoteIdKey"
        }
    }
}