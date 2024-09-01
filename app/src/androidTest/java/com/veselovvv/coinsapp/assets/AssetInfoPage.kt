package com.veselovvv.coinsapp.assets

import com.veselovvv.coinsapp.R
import com.veselovvv.coinsapp.core.AbstractPage
import com.veselovvv.coinsapp.core.ButtonUi
import com.veselovvv.coinsapp.core.ErrorUi
import com.veselovvv.coinsapp.core.SwipeToRefreshUi
import com.veselovvv.coinsapp.core.TextViewUi

class AssetInfoPage : AbstractPage(R.id.asset_info_root_layout) {
    private val swipeToRefreshUi = SwipeToRefreshUi(
        R.id.asset_info_root_layout,
        R.id.asset_info_swipe_to_refresh
    )
    private val errorUi = ErrorUi(R.id.asset_info_fail_layout)
    private val textViewUi = TextViewUi()
    private val buttonUi = ButtonUi()

    fun swipeToRefresh() = swipeToRefreshUi.swipeToRefresh()
    fun checkErrorState(message: String) = errorUi.checkErrorState(message)
    fun clickTryAgainButton() = errorUi.clickTryAgainButton()
    fun scrollUp() = textViewUi.scrollTo(R.id.asset_info_symbol)
    fun clickHistoryButton() = buttonUi.click(R.id.asset_info_history_button)

    fun checkAssetInfoState(
        symbol: String,
        name: String,
        rank: String,
        supply: String,
        maxSupply: String,
        marketCapUsd: String,
        volumeUsd24Hr: String,
        priceUsd: String,
        changePercent24Hr: String,
        vwap24Hr: String
    ) {
        textViewUi.checkText(R.id.asset_info_symbol, symbol)
        textViewUi.checkText(R.id.asset_info_name, name)
        textViewUi.checkText(R.id.asset_info_rank, rank)
        textViewUi.checkText(R.id.asset_info_supply, supply)
        textViewUi.checkText(R.id.asset_info_max_supply, maxSupply)
        textViewUi.checkText(R.id.asset_info_market_cap_usd, marketCapUsd)
        textViewUi.checkText(R.id.asset_info_volume_usd_24_hr, volumeUsd24Hr)
        textViewUi.checkText(R.id.asset_info_price_usd, priceUsd)
        textViewUi.checkText(R.id.asset_info_change_percent_24_hr, changePercent24Hr)
        textViewUi.checkText(R.id.asset_info_vwap_24_hr, vwap24Hr)

        buttonUi.checkIsVisible(R.id.asset_info_history_button)
        buttonUi.checkIsVisible(R.id.asset_info_markets_button)
    }
}
