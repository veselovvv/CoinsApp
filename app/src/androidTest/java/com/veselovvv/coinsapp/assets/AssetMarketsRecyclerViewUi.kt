package com.veselovvv.coinsapp.assets

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import com.veselovvv.coinsapp.R
import com.veselovvv.coinsapp.core.NoResultsRecyclerViewUi
import com.veselovvv.coinsapp.core.withItemViewType
import com.veselovvv.coinsapp.core.withRecyclerViewItemText

class AssetMarketsRecyclerViewUi : NoResultsRecyclerViewUi(
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

    /**
     * Described in getItemViewType() in AssetMarketsAdapter
     */
    companion object {
        private const val BASE_VIEW_TYPE = 0
    }
}
