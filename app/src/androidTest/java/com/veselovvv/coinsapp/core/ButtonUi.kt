package com.veselovvv.coinsapp.core

import android.widget.LinearLayout
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.scrollTo
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withParent
import com.google.android.material.button.MaterialButton
import org.hamcrest.CoreMatchers.allOf

class ButtonUi {
    fun interaction(buttonId: Int) = onView(
        allOf(
            withParent(isAssignableFrom(LinearLayout::class.java)),
            withId(buttonId),
            isAssignableFrom(MaterialButton::class.java)
        )
    )

    fun checkIsVisible(buttonId: Int) {
        interaction(buttonId).perform(scrollTo()).check(matches(isDisplayed()))
    }

    fun click(buttonId: Int) {
        interaction(buttonId).perform(click())
    }
}