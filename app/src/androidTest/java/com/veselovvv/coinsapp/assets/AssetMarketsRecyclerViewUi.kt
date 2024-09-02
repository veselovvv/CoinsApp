package com.veselovvv.coinsapp.assets

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import com.veselovvv.coinsapp.R
import com.veselovvv.coinsapp.core.RecyclerViewUi
import com.veselovvv.coinsapp.core.withItemViewType
import com.veselovvv.coinsapp.core.withRecyclerViewItemText

class AssetMarketsRecyclerViewUi : RecyclerViewUi(
    R.id.asset_markets_swipe_to_refresh,
    R.id.asset_markets_recycler_view
) {
    fun checkAssetMarketsListState(assetMarkets: List<Triple<String, String, String>>) {
        assetMarkets.forEachIndexed { index, (exchangeId, baseId, quoteId) ->
            interaction.perform(scrollToPosition<RecyclerView.ViewHolder>(index))
                .check(matches(withItemViewType(BASE_VIEW_TYPE)))
                .check(matches(isDisplayed()))
                .check(matches(withRecyclerViewItemText(R.id.asset_markets_exchange_id_text_view, exchangeId)))
                .check(matches(withRecyclerViewItemText(R.id.asset_markets_base_id_text_view, baseId)))
                .check(matches(withRecyclerViewItemText(R.id.asset_markets_quote_id_text_view, quoteId)))
        }
    }

    fun checkNoResultsState(text: String) {
        interaction.perform(scrollToPosition<RecyclerView.ViewHolder>(0))
            .check(matches(withItemViewType(NO_RESULTS_VIEW_TYPE)))
            .check(matches(isDisplayed()))
            .check(matches(withRecyclerViewItemText(R.id.no_results_text_view, text)))
    }

    /**
     * Described in getItemViewType() in AssetMarketsAdapter
     */
    companion object {
        private const val NO_RESULTS_VIEW_TYPE = -1
        private const val BASE_VIEW_TYPE = 0
    }
}
