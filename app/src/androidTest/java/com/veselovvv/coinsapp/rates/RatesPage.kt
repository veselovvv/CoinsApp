package com.veselovvv.coinsapp.rates

import com.veselovvv.coinsapp.R
import com.veselovvv.coinsapp.core.AbstractPage
import com.veselovvv.coinsapp.core.SwipeToRefreshUi

class RatesPage : AbstractPage(R.id.rates_root_layout) {
    private val recyclerViewUi = RatesRecyclerViewUi()
    private val swipeToRefreshUi = SwipeToRefreshUi(
        R.id.rates_root_layout,
        R.id.rates_swipe_to_refresh
    )

    fun checkRatesListState(rates: List<Triple<String, String, String>>) =
        recyclerViewUi.checkRatesListState(rates = rates)

    fun swipeToRefresh() = swipeToRefreshUi.swipeToRefresh()
    fun checkErrorState(message: String) = recyclerViewUi.checkErrorState(message = message)
    fun clickTryAgainButton() = recyclerViewUi.clickTryAgainButton()
}
