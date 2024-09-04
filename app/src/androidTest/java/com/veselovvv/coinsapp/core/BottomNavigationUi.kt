package com.veselovvv.coinsapp.core

import androidx.annotation.IdRes
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.veselovvv.coinsapp.R

class BottomNavigationUi {
    fun clickOnTab(@IdRes tabId: Int) {
        onView(withId(tabId)).perform(click())
    }

    fun clickOnRatesTab() = clickOnTab(R.id.ratesFragment)
    fun clickOnExchangesTab() = clickOnTab(R.id.exchangesFragment)
}
