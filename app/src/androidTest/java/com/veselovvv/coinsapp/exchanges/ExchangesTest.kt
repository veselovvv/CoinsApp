package com.veselovvv.coinsapp.exchanges

import androidx.test.espresso.Espresso.pressBack
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

    /**
     * Check Assets Page is visible
     * Check assets list state
     * 1. Click on "Exchanges" tab in BottomNavigation
     * Check Exchanges Page is visible
     * Check exchanges list state
     * 2. Click search button
     * Check search view state
     * 3. CLick back search button
     * Check rates list state
     * 4. Click search button
     * Check search view state
     * 5. Type "Co" in search view
     * Check exchanges list state with found rate
     * 6. CLick back search button
     * Check exchanges list state
     * 7. Click search button
     * Check search view state
     * 8. Type "Smth" in search view
     * Check no results state with text "No results to show"
     * 9. Type "Co" in search view
     * Check exchanges list state with found rate
     * 10. CLick back search button
     * Check exchanges list state
     */
    @Test
    fun searchExchanges() {
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

            clickSearchButton()
            checkSearchViewState()

            clickBackSearchButton()
            checkExchangesListState(
                exchanges = listOf(
                    Triple("Binance", "binance", "1"),
                    Triple("Crypto.com Exchange", "crypto", "2"),
                    Triple("Coinbase Pro", "gdax", "3")
                )
            )

            clickSearchButton()
            checkSearchViewState()

            typeInSearchView(text = "Co")
            checkExchangesListState(
                exchanges = listOf(
                    Triple("Coinbase Pro", "gdax", "3")
                )
            )

            clickBackSearchButton()
            checkExchangesListState(
                exchanges = listOf(
                    Triple("Binance", "binance", "1"),
                    Triple("Crypto.com Exchange", "crypto", "2"),
                    Triple("Coinbase Pro", "gdax", "3")
                )
            )

            clickSearchButton()
            checkSearchViewState()

            typeInSearchView(text = "Smth")
            checkNoResultsState(text = "No results to show")

            typeInSearchView(text = "Co")
            checkExchangesListState(
                exchanges = listOf(
                    Triple("Coinbase Pro", "gdax", "3")
                )
            )

            clickBackSearchButton()
            checkExchangesListState(
                exchanges = listOf(
                    Triple("Binance", "binance", "1"),
                    Triple("Crypto.com Exchange", "crypto", "2"),
                    Triple("Coinbase Pro", "gdax", "3")
                )
            )
        }
    }

    /**
     * Check Assets Page is visible
     * Check assets list state
     * 1. Click on "Exchanges" tab in BottomNavigation
     * Check Exchanges Page is visible
     * Check exchanges list state
     * 2. Click on first item in list (index = 0)
     * Check Exchange Info Page is visible
     * Check exchange info state
     * 3. Recreate activity
     * Check error state with text "No connection. Please try again!"
     * 4. Click "Try again" button
     * Check Exchange Info Page is visible
     * Check exchange info state
     * 5. Swipe to refresh
     * Check error state with text "No connection. Please try again!"
     * 6. Click "Try again" button
     * Check Exchange Info Page is visible
     * Check exchange info state
     * 7. Press back button
     * Check Exchange Info Page is not visible
     * Check Exchanges Page is visible
     * Check error state with text "No connection. Please try again!"
     * 8. Click "Try again" button
     * Check Exchange Info Page is not visible
     * Check Exchanges Page is visible
     * Check exchanges list state
     */
    @Test
    fun loadExchangeInfoAndGoBack() {
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

        val exchangesPage = ExchangesPage()

        with(exchangesPage) {
            checkIsVisible()
            checkExchangesListState(
                exchanges = listOf(
                    Triple("Binance", "binance", "1"),
                    Triple("Crypto.com Exchange", "crypto", "2"),
                    Triple("Coinbase Pro", "gdax", "3")
                )
            )

            clickOnItemInList(index = 0)
        }

        val exchangeInfoPage = ExchangeInfoPage()

        with(exchangeInfoPage) {
            checkIsVisible()
            checkExchangeInfoState(
                name = "Binance",
                rank = "1",
                percentTotalVolume = "2.946801735133553120000000000000000000",
                volumeUsd = "84969370.4499608426167365",
                traidingPairs = "52",
                exchangeUrl = "https://kraken.com"
            )

            activityScenarioRule.scenario.recreate()
            checkErrorState(message = "No connection. Please try again!")

            clickTryAgainButton()
            checkIsVisible()
            checkExchangeInfoState(
                name = "Binance",
                rank = "1",
                percentTotalVolume = "2.946801735133553120000000000000000000",
                volumeUsd = "84969370.4499608426167365",
                traidingPairs = "52",
                exchangeUrl = "https://kraken.com"
            )

            swipeToRefresh()
            checkErrorState(message = "No connection. Please try again!")

            clickTryAgainButton()
            checkIsVisible()
            checkExchangeInfoState(
                name = "Binance",
                rank = "1",
                percentTotalVolume = "2.946801735133553120000000000000000000",
                volumeUsd = "84969370.4499608426167365",
                traidingPairs = "52",
                exchangeUrl = "https://kraken.com"
            )
        }

        pressBack()
        exchangeInfoPage.checkIsNotVisible()

        with(exchangesPage) {
            checkIsVisible()
            checkErrorState(message = "No connection. Please try again!")

            clickTryAgainButton()
        }

        exchangeInfoPage.checkIsNotVisible()

        with(exchangesPage) {
            checkIsVisible()
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