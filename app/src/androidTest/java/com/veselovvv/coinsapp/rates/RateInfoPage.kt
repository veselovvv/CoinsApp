package com.veselovvv.coinsapp.rates

import com.veselovvv.coinsapp.R
import com.veselovvv.coinsapp.core.AbstractPage
import com.veselovvv.coinsapp.core.ErrorUi
import com.veselovvv.coinsapp.core.SwipeToRefreshUi
import com.veselovvv.coinsapp.core.TextViewUi

class RateInfoPage : AbstractPage(R.id.rate_info_root_layout) {
    private val swipeToRefreshUi = SwipeToRefreshUi(
        R.id.rate_info_root_layout,
        R.id.rate_info_swipe_to_refresh
    )
    private val errorUi = ErrorUi(R.id.rate_info_fail_layout)
    private val textViewUi = TextViewUi()

    fun swipeToRefresh() = swipeToRefreshUi.swipeToRefresh()
    fun checkErrorState(message: String) = errorUi.checkErrorState(message)
    fun clickTryAgainButton() = errorUi.clickTryAgainButton()

    fun checkRateInfoState(
        symbol: String,
        name: String,
        currencySymbol: String,
        type: String,
        rateUsd: String
    ) {
        with(textViewUi) {
            checkText(R.id.rate_info_symbol, symbol)
            checkText(R.id.rate_info_name, name)
            checkText(R.id.rate_info_currency_symbol, currencySymbol)
            checkText(R.id.rate_info_type, type)
            checkText(R.id.rate_info_rate_usd, rateUsd)
        }
    }
}
