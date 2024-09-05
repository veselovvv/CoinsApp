package com.veselovvv.coinsapp.markets

import com.veselovvv.coinsapp.R
import com.veselovvv.coinsapp.core.AbstractPage
import com.veselovvv.coinsapp.core.SearchViewUi
import com.veselovvv.coinsapp.core.SwipeToRefreshUi

class MarketsPage : AbstractPage(R.id.markets_root_layout) {
    private val recyclerViewUi = MarketsRecyclerViewUi()
    private val searchViewUi = SearchViewUi()
    private val swipeToRefreshUi = SwipeToRefreshUi(
        R.id.markets_root_layout,
        R.id.markets_swipe_to_refresh
    )

    fun checkMarketsListState(markets: List<Triple<String, String, String>>) =
        recyclerViewUi.checkMarketsListState(markets = markets)

    fun swipeToRefresh() = swipeToRefreshUi.swipeToRefresh()
    fun checkErrorState(message: String) = recyclerViewUi.checkErrorState(message = message)
    fun clickTryAgainButton() = recyclerViewUi.clickTryAgainButton()
    fun clickSearchButton() = searchViewUi.clickSearchButton(searchMenuItemId = R.id.action_search_markets)
    fun checkSearchViewState() = searchViewUi.checkSearchViewState()
    fun clickBackSearchButton() = searchViewUi.clickBackSearchButton()
    fun typeInSearchView(text: String) = searchViewUi.typeInSearchView(text = text)
    fun checkNoResultsState(text: String) = recyclerViewUi.checkNoResultsState(text = text)
}
