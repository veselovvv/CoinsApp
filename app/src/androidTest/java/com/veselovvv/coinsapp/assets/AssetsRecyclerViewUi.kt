package com.veselovvv.coinsapp.assets

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import com.veselovvv.coinsapp.R
import com.veselovvv.coinsapp.core.RecyclerViewUi
import com.veselovvv.coinsapp.core.withItemViewType
import com.veselovvv.coinsapp.core.withRecyclerViewItemText

class AssetsRecyclerViewUi : RecyclerViewUi(
    R.id.assets_swipe_to_refresh,
    R.id.assets_recycler_view
) {
    fun checkAssetsListState(assets: List<Triple<String, String, String>>) {
        assets.forEachIndexed { index, (symbol, name, rank) ->
            interaction.perform(scrollToPosition<RecyclerView.ViewHolder>(index))
                .check(matches(withItemViewType(BASE_VIEW_TYPE)))
                .check(matches(isDisplayed()))
                .check(matches(withRecyclerViewItemText(R.id.asset_symbol_text_view, symbol)))
                .check(matches(withRecyclerViewItemText(R.id.asset_name_text_view, name)))
                .check(matches(withRecyclerViewItemText(R.id.asset_rank_text_view, rank)))
        }
    }

    fun checkNoResultsState(text: String) {
        interaction.perform(scrollToPosition<RecyclerView.ViewHolder>(0))
            .check(matches(withItemViewType(NO_RESULTS_VIEW_TYPE)))
            .check(matches(isDisplayed()))
            .check(matches(withRecyclerViewItemText(R.id.no_results_text_view, text)))
    }

    /**
     * Described in getItemViewType() in AssetsAdapter
     */
    companion object {
        private const val NO_RESULTS_VIEW_TYPE = -1
        private const val BASE_VIEW_TYPE = 0
    }
}
