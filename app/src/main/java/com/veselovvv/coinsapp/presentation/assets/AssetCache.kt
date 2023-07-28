package com.veselovvv.coinsapp.presentation.assets

import android.content.Context
import com.veselovvv.coinsapp.core.Read
import com.veselovvv.coinsapp.core.Save

interface AssetCache : Save<AssetParameters>, Read<AssetParameters> {
    class Base(context: Context) : AssetCache {
        private val sharedPreferences =
            context.getSharedPreferences(ASSET_DATA_FILENAME, Context.MODE_PRIVATE)

        override fun save(data: AssetParameters) =
            sharedPreferences.edit()
                .putString(ASSET_ID_KEY, data.getId())
                .putString(ASSET_RANK_KEY, data.getRank())
                .putString(ASSET_SYMBOL_KEY, data.getSymbol())
                .putString(ASSET_NAME_KEY, data.getName())
                .apply()

        override fun read() = AssetParameters(
            sharedPreferences.getString(ASSET_ID_KEY, "") ?: "",
            sharedPreferences.getString(ASSET_RANK_KEY, "") ?: "",
            sharedPreferences.getString(ASSET_SYMBOL_KEY, "") ?: "",
            sharedPreferences.getString(ASSET_NAME_KEY, "") ?: ""
        )

        companion object {
            private const val ASSET_DATA_FILENAME = "assetData"
            private const val ASSET_ID_KEY = "assetIdKey"
            private const val ASSET_RANK_KEY = "assetRankKey"
            private const val ASSET_SYMBOL_KEY = "assetSymbolKey"
            private const val ASSET_NAME_KEY = "assetNameKey"
        }
    }
}