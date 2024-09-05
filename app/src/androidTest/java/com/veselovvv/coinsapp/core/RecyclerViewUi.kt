package com.veselovvv.coinsapp.core

import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withParent
import com.veselovvv.coinsapp.R
import org.hamcrest.Matchers.allOf

abstract class RecyclerViewUi(swipeToRefreshId: Int, recyclerViewId: Int) {
    protected val interaction: ViewInteraction = onView(
        allOf(
            withParent(withId(swipeToRefreshId)),
            withParent(isAssignableFrom(SwipeRefreshLayout::class.java)),
            withId(recyclerViewId),
            isAssignableFrom(RecyclerView::class.java)
        )
    )

    fun checkErrorState(message: String) {
        interaction.perform(scrollToPosition<RecyclerView.ViewHolder>(0))
            .check(matches(withItemViewType(FAIL_VIEW_TYPE)))
            .check(matches(isDisplayed()))
            .check(matches(withRecyclerViewItemText(R.id.fail_message_text_view, message)))
    }

    fun clickTryAgainButton() {
        interaction.perform(scrollToPosition<RecyclerView.ViewHolder>(0))
            .check(matches(withItemViewType(FAIL_VIEW_TYPE)))
            .perform(clickOnViewChild(R.id.fail_try_again_button))
    }

    fun clickOnItemInList(index: Int) {
        interaction.perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(index, click()))
    }

    /**
     * Described in getItemViewType() in adapter
     */
    companion object {
        private const val FAIL_VIEW_TYPE = 1
    }
}