package com.veselovvv.coinsapp.rates

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import com.veselovvv.coinsapp.R
import com.veselovvv.coinsapp.core.RecyclerViewUi
import com.veselovvv.coinsapp.core.withItemViewType
import com.veselovvv.coinsapp.core.withRecyclerViewItemText

class RatesRecyclerViewUi : RecyclerViewUi(
    R.id.rates_swipe_to_refresh,
    R.id.rates_recycler_view
) {
    fun checkRatesListState(rates: List<Triple<String, String, String>>) {
        rates.forEachIndexed { index, (symbol, rateId, rate) ->
            interaction.perform(scrollToPosition<RecyclerView.ViewHolder>(index))
                .check(matches(withItemViewType(BASE_VIEW_TYPE)))
                .check(matches(isDisplayed()))
                .check(matches(withRecyclerViewItemText(R.id.rate_symbol_text_view, symbol)))
                .check(matches(withRecyclerViewItemText(R.id.rate_id_text_view, rateId)))
                .check(matches(withRecyclerViewItemText(R.id.rate_rate_usd_text_view, rate)))
        }
    }

    //TODO DRY
    fun checkNoResultsState(text: String) {
        interaction.perform(scrollToPosition<RecyclerView.ViewHolder>(0))
            .check(matches(withItemViewType(NO_RESULTS_VIEW_TYPE)))
            .check(matches(isDisplayed()))
            .check(matches(withRecyclerViewItemText(R.id.no_results_text_view, text)))
    }

    /**
     * Described in getItemViewType() in RatesAdapter
     */
    companion object {
        private const val NO_RESULTS_VIEW_TYPE = -1
        private const val BASE_VIEW_TYPE = 0
    }
}
