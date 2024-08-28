package com.veselovvv.coinsapp.assets

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.veselovvv.coinsapp.MainActivity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class AssetsTest {
    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @get:Rule
    val activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun init() {
        hiltRule.inject()
    }

    /**
     * Check assets list state
     * 1. Swipe to refresh
     * Check error state with text "No connection. Please try again!"
     * 2. Click "Try again" button
     * Check assets list state
     * 3. Recreate activity
     * Check error state with text "No connection. Please try again!"
     * 4. Click "Try again" button
     * Check assets list state
     */
    @Test
    fun loadAssets() = with(AssetsPage()) {
        checkAssetsListState(
            assets = listOf(
                Triple("BTC", "Bitcoin", "1"),
                Triple("ETH", "Ethereum", "2"),
                Triple("USDT", "Tether", "3")
            )
        )

        swipeToRefresh()
        checkErrorState(message = "No connection. Please try again!")

        clickTryAgainButton()
        checkAssetsListState(
            assets = listOf(
                Triple("BTC", "Bitcoin", "1"),
                Triple("ETH", "Ethereum", "2"),
                Triple("USDT", "Tether", "3")
            )
        )

        activityScenarioRule.scenario.recreate()
        checkErrorState(message = "No connection. Please try again!")

        clickTryAgainButton()
        checkAssetsListState(
            assets = listOf(
                Triple("BTC", "Bitcoin", "1"),
                Triple("ETH", "Ethereum", "2"),
                Triple("USDT", "Tether", "3")
            )
        )
    }
}