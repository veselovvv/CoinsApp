package com.veselovvv.coinsapp.core

import android.widget.LinearLayout
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.scrollTo
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withParent
import com.google.android.material.button.MaterialButton
import org.hamcrest.CoreMatchers.allOf

class ButtonUi {
    fun checkIsVisible(buttonId: Int) {
        onView(
            allOf(
                withParent(isAssignableFrom(LinearLayout::class.java)),
                withId(buttonId),
                isAssignableFrom(MaterialButton::class.java)
            )
        ).perform(scrollTo()).check(matches(isDisplayed()))
    }
}