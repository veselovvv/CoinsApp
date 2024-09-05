package com.veselovvv.coinsapp.assets

import com.veselovvv.coinsapp.R
import com.veselovvv.coinsapp.core.AbstractPage
import com.veselovvv.coinsapp.core.SearchViewUi
import com.veselovvv.coinsapp.core.SwipeToRefreshUi

class AssetMarketsPage : AbstractPage(R.id.asset_markets_root_layout) {
    private val swipeToRefreshUi = SwipeToRefreshUi(
        R.id.asset_markets_root_layout,
        R.id.asset_markets_swipe_to_refresh
    )
    private val searchViewUi = SearchViewUi()
    private val recyclerViewUi = AssetMarketsRecyclerViewUi()

    fun swipeToRefresh() = swipeToRefreshUi.swipeToRefresh()
    fun checkErrorState(message: String) = recyclerViewUi.checkErrorState(message = message)
    fun clickTryAgainButton() = recyclerViewUi.clickTryAgainButton()
    fun checkNoResultsState(text: String) = recyclerViewUi.checkNoResultsState(text = text)
    fun clickSearchButton() = searchViewUi.clickSearchButton(searchMenuItemId = R.id.action_search_asset_markets)
    fun checkSearchViewState() = searchViewUi.checkSearchViewState()
    fun clickBackSearchButton() = searchViewUi.clickBackSearchButton()
    fun typeInSearchView(text: String) = searchViewUi.typeInSearchView(text = text)

    fun checkAssetMarketsListState(assetMarkets: List<Triple<String, String, String>>) =
        recyclerViewUi.checkAssetMarketsListState(assetMarkets = assetMarkets)
}
