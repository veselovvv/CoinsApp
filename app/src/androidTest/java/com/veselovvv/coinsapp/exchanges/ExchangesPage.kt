package com.veselovvv.coinsapp.exchanges

import com.veselovvv.coinsapp.R
import com.veselovvv.coinsapp.core.AbstractPage
import com.veselovvv.coinsapp.core.SwipeToRefreshUi

class ExchangesPage : AbstractPage(R.id.exchanges_root_layout) {
    private val recyclerViewUi = ExchangesRecyclerViewUi()
    private val swipeToRefreshUi = SwipeToRefreshUi(
        R.id.exchanges_root_layout,
        R.id.exchanges_swipe_to_refresh
    )

    fun swipeToRefresh() = swipeToRefreshUi.swipeToRefresh()
    fun checkErrorState(message: String) = recyclerViewUi.checkErrorState(message = message)
    fun clickTryAgainButton() = recyclerViewUi.clickTryAgainButton()

    fun checkExchangesListState(exchanges: List<Triple<String, String, String>>) =
        recyclerViewUi.checkExchangesListState(exchanges = exchanges)
}
