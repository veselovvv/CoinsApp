package com.veselovvv.coinsapp.assets

import com.veselovvv.coinsapp.R
import com.veselovvv.coinsapp.core.AbstractPage
import com.veselovvv.coinsapp.core.SwipeToRefreshUi

class AssetHistoryPage : AbstractPage(R.id.asset_history_root_layout) {
    private val swipeToRefreshUi = SwipeToRefreshUi(
        R.id.asset_history_root_layout,
        R.id.asset_history_swipe_to_refresh
    )
    private val recyclerViewUi = AssetHistoryRecyclerViewUi()

    fun swipeToRefresh() = swipeToRefreshUi.swipeToRefresh()
    fun checkErrorState(message: String) = recyclerViewUi.checkErrorState(message = message)
    fun clickTryAgainButton() = recyclerViewUi.clickTryAgainButton()

    fun checkAssetHistoryListState(assetHistory: List<Pair<String, String>>) =
        recyclerViewUi.checkAssetHistoryListState(assetHistory = assetHistory)
}
