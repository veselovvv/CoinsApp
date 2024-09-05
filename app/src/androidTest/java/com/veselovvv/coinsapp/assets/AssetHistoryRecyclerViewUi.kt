package com.veselovvv.coinsapp.assets

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import com.veselovvv.coinsapp.R
import com.veselovvv.coinsapp.core.RecyclerViewUi
import com.veselovvv.coinsapp.core.withItemViewType
import com.veselovvv.coinsapp.core.withRecyclerViewItemText

class AssetHistoryRecyclerViewUi : RecyclerViewUi(
    R.id.asset_history_swipe_to_refresh,
    R.id.asset_history_recycler_view
) {
    fun checkAssetHistoryListState(assetHistory: List<Pair<String, String>>) {
        assetHistory.forEachIndexed { index, (price, time) ->
            interaction.perform(scrollToPosition<RecyclerView.ViewHolder>(index))
                .check(matches(withItemViewType(BASE_VIEW_TYPE)))
                .check(matches(isDisplayed()))
                .check(matches(withRecyclerViewItemText(R.id.asset_history_price_usd_text_view, price)))
                .check(matches(withRecyclerViewItemText(R.id.asset_history_time_text_view, time)))
        }
    }

    /**
     * Described in getItemViewType() in AssetHistoryAdapter
     */
    companion object {
        private const val BASE_VIEW_TYPE = 0
    }
}
