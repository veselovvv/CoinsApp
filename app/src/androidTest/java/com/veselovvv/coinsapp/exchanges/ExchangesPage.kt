package com.veselovvv.coinsapp.exchanges

import com.veselovvv.coinsapp.R
import com.veselovvv.coinsapp.core.AbstractPage
import com.veselovvv.coinsapp.core.SearchViewUi
import com.veselovvv.coinsapp.core.SwipeToRefreshUi

class ExchangesPage : AbstractPage(R.id.exchanges_root_layout) {
    private val recyclerViewUi = ExchangesRecyclerViewUi()
    private val searchViewUi = SearchViewUi()
    private val swipeToRefreshUi = SwipeToRefreshUi(
        R.id.exchanges_root_layout,
        R.id.exchanges_swipe_to_refresh
    )

    fun swipeToRefresh() = swipeToRefreshUi.swipeToRefresh()
    fun checkErrorState(message: String) = recyclerViewUi.checkErrorState(message = message)
    fun clickTryAgainButton() = recyclerViewUi.clickTryAgainButton()
    fun clickOnItemInList(index: Int) = recyclerViewUi.clickOnItemInList(index)
    fun clickSearchButton() = searchViewUi.clickSearchButton(searchMenuItemId = R.id.action_search_exchanges)
    fun checkSearchViewState() = searchViewUi.checkSearchViewState()
    fun clickBackSearchButton() = searchViewUi.clickBackSearchButton()
    fun typeInSearchView(text: String) = searchViewUi.typeInSearchView(text = text)
    fun checkNoResultsState(text: String) = recyclerViewUi.checkNoResultsState(text = text)

    fun checkExchangesListState(exchanges: List<Triple<String, String, String>>) =
        recyclerViewUi.checkExchangesListState(exchanges = exchanges)
}
