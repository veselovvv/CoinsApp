package com.veselovvv.coinsapp.rates

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
class RatesTest {
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
     * 1. Click on "Rates" tab in BottomNavigation
     * Check Rates Page is visible
     * Check rates list state
     * 2. Swipe to refresh
     * Check error state with text "No connection. Please try again!"
     * 3. Click "Try again" button
     * Check rates list state
     * 4. Recreate activity
     * Check error state with text "No connection. Please try again!"
     * 5. Click "Try again" button
     * Check rates list state
     */
    @Test
    fun loadRates() {
        with(AssetsPage()) {
            checkIsVisible()
            checkAssetsListState(
                assets = listOf(
                    Triple("BTC", "Bitcoin", "1"),
                    Triple("ETH", "Ethereum", "2"),
                    Triple("USDT", "Tether", "3")
                )
            )

            clickOnRatesTab()
        }

        with(RatesPage()) {
            checkIsVisible()
            checkRatesListState(
                rates = listOf(
                    Triple("BMD", "bermudan-dollar", "1.0000000000000000"),
                    Triple("NOK", "norwegian-krone", "0.0944781311469834"),
                    Triple("HKD", "hong-kong-dollar", "0.1282511835339845")
                )
            )

            swipeToRefresh()
            checkErrorState(message = "No connection. Please try again!")

            clickTryAgainButton()
            checkRatesListState(
                rates = listOf(
                    Triple("BMD", "bermudan-dollar", "1.0000000000000000"),
                    Triple("NOK", "norwegian-krone", "0.0944781311469834"),
                    Triple("HKD", "hong-kong-dollar", "0.1282511835339845")
                )
            )

            activityScenarioRule.scenario.recreate()
            checkErrorState(message = "No connection. Please try again!")

            clickTryAgainButton()
            checkRatesListState(
                rates = listOf(
                    Triple("BMD", "bermudan-dollar", "1.0000000000000000"),
                    Triple("NOK", "norwegian-krone", "0.0944781311469834"),
                    Triple("HKD", "hong-kong-dollar", "0.1282511835339845")
                )
            )
        }
    }

    // TODO RUN
    /**
     * Check Assets Page is visible
     * Check assets list state
     * 1. Click on "Rates" tab in BottomNavigation
     * Check Rates Page is visible
     * Check rates list state
     * 2. Click search button
     * Check search view state
     * 3. CLick back search button
     * Check rates list state
     * 4. Click search button
     * Check search view state
     * 5. Type "HK" in search view
     * Check rates list state with found rate
     * 6. CLick back search button
     * Check rates list state
     * 7. Click search button
     * Check search view state
     * 8. Type "Smth" in search view
     * Check no results state with text "No results to show"
     * 9. Type "HK" in search view
     * Check rates list state with found rate
     * 10. CLick back search button
     * Check rates list state
     */
    @Test
    fun searchRates() {
        with(AssetsPage()) {
            checkIsVisible()
            checkAssetsListState(
                assets = listOf(
                    Triple("BTC", "Bitcoin", "1"),
                    Triple("ETH", "Ethereum", "2"),
                    Triple("USDT", "Tether", "3")
                )
            )

            clickOnRatesTab()
        }

        with(RatesPage()) {
            checkIsVisible()
            checkRatesListState(
                rates = listOf(
                    Triple("BMD", "bermudan-dollar", "1.0000000000000000"),
                    Triple("NOK", "norwegian-krone", "0.0944781311469834"),
                    Triple("HKD", "hong-kong-dollar", "0.1282511835339845")
                )
            )

            clickSearchButton()
            checkSearchViewState()

            clickBackSearchButton()
            checkRatesListState(
                rates = listOf(
                    Triple("BMD", "bermudan-dollar", "1.0000000000000000"),
                    Triple("NOK", "norwegian-krone", "0.0944781311469834"),
                    Triple("HKD", "hong-kong-dollar", "0.1282511835339845")
                )
            )

            clickSearchButton()
            checkSearchViewState()

            typeInSearchView(text = "HK")
            checkRatesListState(
                rates = listOf(
                    Triple("HKD", "hong-kong-dollar", "0.1282511835339845")
                )
            )

            clickBackSearchButton()
            checkRatesListState(
                rates = listOf(
                    Triple("BMD", "bermudan-dollar", "1.0000000000000000"),
                    Triple("NOK", "norwegian-krone", "0.0944781311469834"),
                    Triple("HKD", "hong-kong-dollar", "0.1282511835339845")
                )
            )

            clickSearchButton()
            checkSearchViewState()

            typeInSearchView(text = "Smth")
            checkNoResultsState(text = "No results to show")

            typeInSearchView(text = "HK")
            checkRatesListState(
                rates = listOf(
                    Triple("HKD", "hong-kong-dollar", "0.1282511835339845")
                )
            )

            clickBackSearchButton()
            checkRatesListState(
                rates = listOf(
                    Triple("BMD", "bermudan-dollar", "1.0000000000000000"),
                    Triple("NOK", "norwegian-krone", "0.0944781311469834"),
                    Triple("HKD", "hong-kong-dollar", "0.1282511835339845")
                )
            )
        }
    }
}