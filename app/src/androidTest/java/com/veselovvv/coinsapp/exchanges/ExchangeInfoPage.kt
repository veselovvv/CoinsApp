package com.veselovvv.coinsapp.exchanges

import com.veselovvv.coinsapp.R
import com.veselovvv.coinsapp.core.AbstractPage
import com.veselovvv.coinsapp.core.ErrorUi
import com.veselovvv.coinsapp.core.SwipeToRefreshUi
import com.veselovvv.coinsapp.core.TextViewUi

class ExchangeInfoPage : AbstractPage(R.id.exchange_info_root_layout) {
    private val swipeToRefreshUi = SwipeToRefreshUi(
        R.id.exchange_info_root_layout,
        R.id.exchange_info_swipe_to_refresh
    )
    private val errorUi = ErrorUi(R.id.exchange_info_fail_layout)
    private val textViewUi = TextViewUi()

    fun swipeToRefresh() = swipeToRefreshUi.swipeToRefresh()
    fun checkErrorState(message: String) = errorUi.checkErrorState(message)
    fun clickTryAgainButton() = errorUi.clickTryAgainButton()

    fun checkExchangeInfoState(
        name: String,
        rank: String,
        percentTotalVolume: String,
        volumeUsd: String,
        traidingPairs: String,
        exchangeUrl: String
    ) {
        with(textViewUi) {
            checkText(R.id.exchange_info_name, name)
            checkText(R.id.exchange_info_rank, rank)
            checkText(R.id.exchange_info_percent_total_volume, percentTotalVolume)
            checkText(R.id.exchange_info_volume_usd, volumeUsd)
            checkText(R.id.exchange_info_trading_pairs, traidingPairs)
            checkText(R.id.exchange_info_exchange_url, exchangeUrl)
        }
    }
}
