package com.veselovvv.coinsapp.presentation.exchanges

import android.content.Context
import com.veselovvv.coinsapp.core.Read
import com.veselovvv.coinsapp.core.Save

interface ExchangeCache : Save<ExchangesParameters>, Read<ExchangesParameters> {
    class Base(context: Context) : ExchangeCache {
        private val sharedPreferences =
            context.getSharedPreferences(EXCHANGE_DATA_FILENAME, Context.MODE_PRIVATE)

        override fun save(data: ExchangesParameters) =
            sharedPreferences.edit()
                .putString(EXCHANGE_ID_KEY, data.getId())
                .putString(EXCHANGE_NAME_KEY, data.getName())
                .putString(EXCHANGE_RANK_KEY, data.getRank())
                .apply()

        override fun read(): ExchangesParameters = ExchangesParameters(
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