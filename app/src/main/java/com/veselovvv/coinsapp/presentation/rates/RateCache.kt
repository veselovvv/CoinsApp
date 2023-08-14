package com.veselovvv.coinsapp.presentation.rates

import android.content.Context
import com.veselovvv.coinsapp.core.Read
import com.veselovvv.coinsapp.core.Save

interface RateCache : Save<Triple<String, String, String>>, Read<Triple<String, String, String>> {
    class Base(context: Context) : RateCache {
        private val sharedPreferences =
            context.getSharedPreferences(RATE_DATA_FILENAME, Context.MODE_PRIVATE)

        override fun save(data: Triple<String, String, String>) =
            sharedPreferences.edit()
                .putString(RATE_ID_KEY, data.first)
                .putString(RATE_SYMBOL_KEY, data.second)
                .putString(RATE_RATE_USD_KEY, data.third)
                .apply()

        override fun read() = Triple(
            sharedPreferences.getString(RATE_ID_KEY, "") ?: "",
            sharedPreferences.getString(RATE_SYMBOL_KEY, "") ?: "",
            sharedPreferences.getString(RATE_RATE_USD_KEY, "") ?: ""
        )

        companion object {
            private const val RATE_DATA_FILENAME = "rateData"
            private const val RATE_ID_KEY = "rateIdKey"
            private const val RATE_SYMBOL_KEY = "rateSymbolKey"
            private const val RATE_RATE_USD_KEY = "rateRateUsdKey"
        }
    }
}