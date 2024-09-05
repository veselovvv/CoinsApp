package com.veselovvv.coinsapp.markets

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import com.veselovvv.coinsapp.R
import com.veselovvv.coinsapp.core.NoResultsRecyclerViewUi
import com.veselovvv.coinsapp.core.withItemViewType
import com.veselovvv.coinsapp.core.withRecyclerViewItemText

class MarketsRecyclerViewUi : NoResultsRecyclerViewUi(
    R.id.markets_swipe_to_refresh,
    R.id.markets_recycler_view
) {
    fun checkMarketsListState(markets: List<Triple<String, String, String>>) {
        markets.forEachIndexed { index, (id, baseSymbol, quoteSymbol) ->
            interaction.perform(scrollToPosition<RecyclerView.ViewHolder>(index))
                .check(matches(withItemViewType(BASE_VIEW_TYPE)))
                .check(matches(isDisplayed()))
                .check(matches(withRecyclerViewItemText(R.id.exchange_id_text_view, id)))
                .check(matches(withRecyclerViewItemText(R.id.base_symbol_text_view, baseSymbol)))
                .check(matches(withRecyclerViewItemText(R.id.quote_symbol_text_view, quoteSymbol)))
        }
    }

    /**
     * Described in getItemViewType() in MarketsAdapter
     */
    companion object {
        private const val BASE_VIEW_TYPE = 0
    }
}
