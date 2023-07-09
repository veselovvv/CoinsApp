package com.veselovvv.coinsapp.presentation.assets

import android.content.Context
import com.veselovvv.coinsapp.core.Read
import com.veselovvv.coinsapp.core.Save

interface AssetCache : Save<Triple<String, String, String>>, Read<Triple<String, String, String>> {
    class Base(context: Context) : AssetCache {
        private val sharedPreferences =
            context.getSharedPreferences(ASSET_DATA_FILENAME, Context.MODE_PRIVATE)

        override fun save(data: Triple<String, String, String>) =
            sharedPreferences.edit()
                .putString(ASSET_RANK_KEY, data.first)
                .putString(ASSET_SYMBOL_KEY, data.second)
                .putString(ASSET_NAME_KEY, data.third)
                .apply()

        override fun read() = Triple(
            sharedPreferences.getString(ASSET_RANK_KEY, "") ?: "",
            sharedPreferences.getString(ASSET_SYMBOL_KEY, "") ?: "",
            sharedPreferences.getString(ASSET_NAME_KEY, "") ?: ""
        )

        companion object {
            private const val ASSET_DATA_FILENAME = "assetData"
            private const val ASSET_RANK_KEY = "assetRankKey"
            private const val ASSET_SYMBOL_KEY = "assetSymbolKey"
            private const val ASSET_NAME_KEY = "assetNameKey"
        }
    }
}