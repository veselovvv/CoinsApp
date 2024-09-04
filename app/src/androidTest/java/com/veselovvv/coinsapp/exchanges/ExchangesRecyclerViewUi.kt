package com.veselovvv.coinsapp.exchanges

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import com.veselovvv.coinsapp.R
import com.veselovvv.coinsapp.core.NoResultsRecyclerViewUi
import com.veselovvv.coinsapp.core.withItemViewType
import com.veselovvv.coinsapp.core.withRecyclerViewItemText

class ExchangesRecyclerViewUi : NoResultsRecyclerViewUi(
    R.id.exchanges_swipe_to_refresh,
    R.id.exchanges_recycler_view
) {
    fun checkExchangesListState(exchanges: List<Triple<String, String, String>>) {
        exchanges.forEachIndexed { index, (name, id, rank) ->
            interaction.perform(scrollToPosition<RecyclerView.ViewHolder>(index))
                .check(matches(withItemViewType(BASE_VIEW_TYPE)))
                .check(matches(isDisplayed()))
                .check(matches(withRecyclerViewItemText(R.id.exchange_name_text_view, name)))
                .check(matches(withRecyclerViewItemText(R.id.exchange_id_text_view, id)))
                .check(matches(withRecyclerViewItemText(R.id.exchange_rank_text_view, rank)))
        }
    }

    /**
     * Described in getItemViewType() in ExchanhesAdapter
     */
    companion object {
        private const val BASE_VIEW_TYPE = 0
    }
}
