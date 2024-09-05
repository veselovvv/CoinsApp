package com.veselovvv.coinsapp.core

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import com.veselovvv.coinsapp.R

abstract class NoResultsRecyclerViewUi(
    swipeToRefreshId: Int,
    recyclerViewId: Int
) : RecyclerViewUi(swipeToRefreshId, recyclerViewId) {
    fun checkNoResultsState(text: String) {
        interaction.perform(scrollToPosition<RecyclerView.ViewHolder>(0))
            .check(matches(withItemViewType(NO_RESULTS_VIEW_TYPE)))
            .check(matches(isDisplayed()))
            .check(matches(withRecyclerViewItemText(R.id.no_results_text_view, text)))
    }

    /**
     * Described in getItemViewType() in Adapter
     */
    companion object {
        private const val NO_RESULTS_VIEW_TYPE = -1
    }
}