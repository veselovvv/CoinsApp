package com.veselovvv.coinsapp.markets

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.veselovvv.coinsapp.MainActivity
import com.veselovvv.coinsapp.assets.AssetsPage
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class MarketsTest {
    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @get:Rule
    val activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun init() {
        hiltRule.inject()
    }

    /**
     * Check Assets Page is visible
     * Check assets list state
     * 1. Click on "Markets" tab in BottomNavigation
     * Check Markets Page is visible
     * Check markets list state
     * 2. Swipe to refresh
     * Check error state with text "No connection. Please try again!"
     * 3. Click "Try again" button
     * Check markets list state
     * 4. Recreate activity
     * Check error state with text "No connection. Please try again!"
     * 5. Click "Try again" button
     * Check markets list state
     */
    @Test
    fun loadMarkets() {
        with(AssetsPage()) {
            checkIsVisible()
            checkAssetsListState(
                assets = listOf(
                    Triple("BTC", "Bitcoin", "1"),
                    Triple("ETH", "Ethereum", "2"),
                    Triple("USDT", "Tether", "3")
                )
            )

            clickOnMarketsTab()
        }

        with(MarketsPage()) {
            checkIsVisible()
            checkMarketsListState(
                markets = listOf(
                    Triple("alterdice", "BTC", "USDT"),
                    Triple("alterdice", "LTC", "USDT"),
                    Triple("alterdice", "FTM", "USDT")
                )
            )

            swipeToRefresh()
            checkErrorState(message = "No connection. Please try again!")

            clickTryAgainButton()
            checkMarketsListState(
                markets = listOf(
                    Triple("alterdice", "BTC", "USDT"),
                    Triple("alterdice", "LTC", "USDT"),
                    Triple("alterdice", "FTM", "USDT")
                )
            )

            activityScenarioRule.scenario.recreate()
            checkErrorState(message = "No connection. Please try again!")

            clickTryAgainButton()
            checkMarketsListState(
                markets = listOf(
                    Triple("alterdice", "BTC", "USDT"),
                    Triple("alterdice", "LTC", "USDT"),
                    Triple("alterdice", "FTM", "USDT")
                )
            )
        }
    }
}