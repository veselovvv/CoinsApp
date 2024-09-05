package com.veselovvv.coinsapp.core

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.matcher.BoundedMatcher
import org.hamcrest.Description
import org.hamcrest.Matcher

fun withItemViewType(viewType: Int): Matcher<View> {
    return object : BoundedMatcher<View, RecyclerView>(RecyclerView::class.java) {
        override fun describeTo(description: Description) {
            description.appendText("RecyclerView with item view type: $viewType")
        }

        override fun matchesSafely(recyclerView: RecyclerView): Boolean {
            val adapter = recyclerView.adapter

            for (i in 0 until adapter!!.itemCount) {
                if (adapter.getItemViewType(i) == viewType) {
                    return true
                }
            }

            return false
        }
    }
}
