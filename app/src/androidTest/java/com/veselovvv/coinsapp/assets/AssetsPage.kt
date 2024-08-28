package com.veselovvv.coinsapp.assets

import com.veselovvv.coinsapp.R
import com.veselovvv.coinsapp.core.SwipeToRefreshUi

class AssetsPage {
    private val recyclerViewUi = AssetsRecyclerViewUi()
    private val swipeToRefreshUi = SwipeToRefreshUi(
        R.id.assets_root_layout,
        R.id.assets_swipe_to_refresh
    )

    fun checkAssetsListState(assets: List<Triple<String, String, String>>) =
        recyclerViewUi.checkAssetsListState(assets = assets)

    fun swipeToRefresh() = swipeToRefreshUi.swipeToRefresh()
    fun checkErrorState(message: String) = recyclerViewUi.checkErrorState(message = message)
    fun clickTryAgainButton() = recyclerViewUi.clickTryAgainButton()
}
