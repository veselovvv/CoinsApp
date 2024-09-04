package com.veselovvv.coinsapp.exchanges

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
class ExchangesTest {
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
     * 1. Click on "Exchanges" tab in BottomNavigation
     * Check Exchanges Page is visible
     * Check exchanges list state
     * 2. Swipe to refresh
     * Check error state with text "No connection. Please try again!"
     * 3. Click "Try again" button
     * Check exchanges list state
     * 4. Recreate activity
     * Check error state with text "No connection. Please try again!"
     * 5. Click "Try again" button
     * Check exchanges list state
     */
    @Test
    fun loadExchanges() {
        with(AssetsPage()) {
            checkIsVisible()
            checkAssetsListState(
                assets = listOf(
                    Triple("BTC", "Bitcoin", "1"),
                    Triple("ETH", "Ethereum", "2"),
                    Triple("USDT", "Tether", "3")
                )
            )

            clickOnExchangesTab()
        }

        with(ExchangesPage()) {
            checkIsVisible()
            checkExchangesListState(
                exchanges = listOf(
                    Triple("Binance", "binance", "1"),
                    Triple("Crypto.com Exchange", "crypto", "2"),
                    Triple("Coinbase Pro", "gdax", "3")
                )
            )

            swipeToRefresh()
            checkErrorState(message = "No connection. Please try again!")

            clickTryAgainButton()
            checkExchangesListState(
                exchanges = listOf(
                    Triple("Binance", "binance", "1"),
                    Triple("Crypto.com Exchange", "crypto", "2"),
                    Triple("Coinbase Pro", "gdax", "3")
                )
            )

            activityScenarioRule.scenario.recreate()
            checkErrorState(message = "No connection. Please try again!")

            clickTryAgainButton()
            checkExchangesListState(
                exchanges = listOf(
                    Triple("Binance", "binance", "1"),
                    Triple("Crypto.com Exchange", "crypto", "2"),
                    Triple("Coinbase Pro", "gdax", "3")
                )
            )
        }
    }
}