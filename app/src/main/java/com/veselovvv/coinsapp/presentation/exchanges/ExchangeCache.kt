package com.veselovvv.coinsapp.presentation.exchanges

import android.content.Context
import com.veselovvv.coinsapp.core.Read
import com.veselovvv.coinsapp.core.Save

interface ExchangeCache : Save<Triple<String, String, String>>, Read<Triple<String, String, String>> {
    class Base(context: Context) : ExchangeCache {
        private val sharedPreferences =
            context.getSharedPreferences(EXCHANGE_DATA_FILENAME, Context.MODE_PRIVATE)

        override fun save(data: Triple<String, String, String>) =
            sharedPreferences.edit()
                .putString(EXCHANGE_ID_KEY, data.first)
                .putString(EXCHANGE_NAME_KEY, data.second)
                .putString(EXCHANGE_RANK_KEY, data.third)
                .apply()

        override fun read(): Triple<String, String, String> = Triple(
            sharedPreferences.getString(EXCHANGE_ID_KEY, "") ?: "",
            sharedPreferences.getString(EXCHANGE_NAME_KEY, "") ?: "",
            sharedPreferences.getString(EXCHANGE_RANK_KEY, "") ?: ""
        )

        companion object {
            private const val EXCHANGE_DATA_FILENAME = "exchangeData"
            private const val EXCHANGE_ID_KEY = "exchangeIdKey"
            private const val EXCHANGE_NAME_KEY = "exchangeNameKey"
            private const val EXCHANGE_RANK_KEY = "exchangeRankKey"
        }
    }
}