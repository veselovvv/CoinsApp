package com.veselovvv.coinsapp.assets

import com.veselovvv.coinsapp.R
import com.veselovvv.coinsapp.core.AbstractPage
import com.veselovvv.coinsapp.core.BottomNavigationUi
import com.veselovvv.coinsapp.core.SearchViewUi
import com.veselovvv.coinsapp.core.SwipeToRefreshUi

class AssetsPage : AbstractPage(R.id.assets_root_layout) {
    private val recyclerViewUi = AssetsRecyclerViewUi()
    private val searchViewUi = SearchViewUi()
    private val bottomNavigationUi = BottomNavigationUi()
    private val swipeToRefreshUi = SwipeToRefreshUi(
        R.id.assets_root_layout,
        R.id.assets_swipe_to_refresh
    )

    fun checkAssetsListState(assets: List<Triple<String, String, String>>) =
        recyclerViewUi.checkAssetsListState(assets = assets)

    fun swipeToRefresh() = swipeToRefreshUi.swipeToRefresh()
    fun checkErrorState(message: String) = recyclerViewUi.checkErrorState(message = message)
    fun clickTryAgainButton() = recyclerViewUi.clickTryAgainButton()
    fun clickSearchButton() = searchViewUi.clickSearchButton(searchMenuItemId = R.id.action_search_assets)
    fun checkSearchViewState() = searchViewUi.checkSearchViewState()
    fun clickBackSearchButton() = searchViewUi.clickBackSearchButton()
    fun typeInSearchView(text: String) = searchViewUi.typeInSearchView(text = text)
    fun checkNoResultsState(text: String) = recyclerViewUi.checkNoResultsState(text = text)
    fun clickOnItemInList(index: Int) = recyclerViewUi.clickOnItemInList(index)
    fun clickOnRatesTab() = bottomNavigationUi.clickOnRatesTab()
    fun clickOnExchangesTab() = bottomNavigationUi.clickOnExchangesTab()
}
