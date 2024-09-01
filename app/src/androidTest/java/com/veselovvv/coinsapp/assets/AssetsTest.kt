package com.veselovvv.coinsapp.assets

import androidx.test.espresso.Espresso.pressBack
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

    /**
     * Check assets list state
     * 1. Click search button
     * Check search view state
     * 2. CLick back search button
     * Check assets list state
     * 3. Click search button
     * Check search view state
     * 4. Type "Bit" in search view
     * Check assets list state with found asset
     * 5. CLick back search button
     * Check assets list state
     * 6. Click search button
     * Check search view state
     * 7. Type "Smth" in search view
     * Check no results state with text "No results to show"
     * 8. Type "Bit" in search view
     * Check assets list state with found asset
     * 9. CLick back search button
     * Check assets list state
     */
    @Test
    fun searchAssets() = with(AssetsPage()) {
        checkAssetsListState(
            assets = listOf(
                Triple("BTC", "Bitcoin", "1"),
                Triple("ETH", "Ethereum", "2"),
                Triple("USDT", "Tether", "3")
            )
        )

        clickSearchButton()
        checkSearchViewState()

        clickBackSearchButton()
        checkAssetsListState(
            assets = listOf(
                Triple("BTC", "Bitcoin", "1"),
                Triple("ETH", "Ethereum", "2"),
                Triple("USDT", "Tether", "3")
            )
        )

        clickSearchButton()
        checkSearchViewState()

        typeInSearchView(text = "Bit")
        checkAssetsListState(
            assets = listOf(
                Triple("BTC", "Bitcoin", "1")
            )
        )

        clickBackSearchButton()
        checkAssetsListState(
            assets = listOf(
                Triple("BTC", "Bitcoin", "1"),
                Triple("ETH", "Ethereum", "2"),
                Triple("USDT", "Tether", "3")
            )
        )

        clickSearchButton()
        checkSearchViewState()

        typeInSearchView(text = "Smth")
        checkNoResultsState(text = "No results to show")

        typeInSearchView(text = "Bit")
        checkAssetsListState(
            assets = listOf(
                Triple("BTC", "Bitcoin", "1")
            )
        )

        clickBackSearchButton()
        checkAssetsListState(
            assets = listOf(
                Triple("BTC", "Bitcoin", "1"),
                Triple("ETH", "Ethereum", "2"),
                Triple("USDT", "Tether", "3")
            )
        )
    }

    /**
     * Check Assets Page is visible
     * Check assets list state
     * 1. Click on first item in list (index = 0)
     * Check Asset Info Page is visible
     * Check asset info state
     * 2. Recreate activity
     * Check error state with text "No connection. Please try again!"
     * 3. Click "Try again" button
     * Check Asset Info Page is visible
     * Check asset info state
     * 4. Scroll up
     * 5. Swipe to refresh
     * Check error state with text "No connection. Please try again!"
     * 6. Click "Try again" button
     * Check Asset Info Page is visible
     * Check asset info state
     * 7. Press back button
     * Check Asset Info Page is not visible
     * Check Assets Page is visible
     * Check error state with text "No connection. Please try again!"
     * 8. Click "Try again" button
     * Check Asset Info Page is not visible
     * Check Assets Page is visible
     * Check assets list state
     */
    @Test
    fun loadAssetInfoAndGoBack() {
        val assetsPage = AssetsPage()

        with(assetsPage) {
            checkIsVisible()
            checkAssetsListState(
                assets = listOf(
                    Triple("BTC", "Bitcoin", "1"),
                    Triple("ETH", "Ethereum", "2"),
                    Triple("USDT", "Tether", "3")
                )
            )
            clickOnItemInList(index = 0)
        }

        val assetInfoPage = AssetInfoPage()

        with(assetInfoPage) {
            checkIsVisible()
            checkAssetInfoState(
                symbol = "BTC",
                name = "Bitcoin",
                rank = "1",
                supply = "17193925.0000000000000000",
                maxSupply = "21000000.0000000000000000",
                marketCapUsd = "119179791817.6740161068269075",
                volumeUsd24Hr = "2928356777.6066665425687196",
                priceUsd = "6931.5058555666618359",
                changePercent24Hr = "-0.8101417214350335",
                vwap24Hr = "7175.0663247679233209"
            )

            activityScenarioRule.scenario.recreate()
            checkErrorState(message = "No connection. Please try again!")

            clickTryAgainButton()
            checkIsVisible()
            checkAssetInfoState(
                symbol = "BTC",
                name = "Bitcoin",
                rank = "1",
                supply = "17193925.0000000000000000",
                maxSupply = "21000000.0000000000000000",
                marketCapUsd = "119179791817.6740161068269075",
                volumeUsd24Hr = "2928356777.6066665425687196",
                priceUsd = "6931.5058555666618359",
                changePercent24Hr = "-0.8101417214350335",
                vwap24Hr = "7175.0663247679233209"
            )

            scrollUp()
            swipeToRefresh()
            checkErrorState(message = "No connection. Please try again!")

            clickTryAgainButton()
            checkIsVisible()
            checkAssetInfoState(
                symbol = "BTC",
                name = "Bitcoin",
                rank = "1",
                supply = "17193925.0000000000000000",
                maxSupply = "21000000.0000000000000000",
                marketCapUsd = "119179791817.6740161068269075",
                volumeUsd24Hr = "2928356777.6066665425687196",
                priceUsd = "6931.5058555666618359",
                changePercent24Hr = "-0.8101417214350335",
                vwap24Hr = "7175.0663247679233209"
            )
        }

        pressBack()
        assetInfoPage.checkIsNotVisible()

        with(assetsPage) {
            checkIsVisible()
            checkErrorState(message = "No connection. Please try again!")

            clickTryAgainButton()
        }

        assetInfoPage.checkIsNotVisible()

        with(assetsPage) {
            checkIsVisible()
            checkAssetsListState(
                assets = listOf(
                    Triple("BTC", "Bitcoin", "1"),
                    Triple("ETH", "Ethereum", "2"),
                    Triple("USDT", "Tether", "3")
                )
            )
        }
    }

    /**
     * Check Assets Page is visible
     * Check assets list state
     * 1. Click on first item in list (index = 0)
     * Check Asset Info Page is visible
     * Check asset info state
     * 2. Click "History" button
     * Check Asset Info Page is not visible
     * Check Asset History Page is visible
     * Check asset history list state
     * 3. Swipe to refresh
     * Check error state with text "No connection. Please try again!"
     * 4. Click "Try again" button
     * Check Asset Info Page is not visible
     * Check Asset History Page is visible
     * Check asset history list state
     * 5. Press back button
     * Check Asset History Page is not visible
     * Check Asset Info Page is visible
     * Check error state with text "No connection. Please try again!"
     * 6. Click "Try again" button
     * Check Asset History Page is not visible
     * Check Asset Info Page is visible
     * Check asset info state
     */
    @Test
    fun loadAssetHistoryAndGoBack() {
        val assetsPage = AssetsPage()

        with(assetsPage) {
            checkIsVisible()
            checkAssetsListState(
                assets = listOf(
                    Triple("BTC", "Bitcoin", "1"),
                    Triple("ETH", "Ethereum", "2"),
                    Triple("USDT", "Tether", "3")
                )
            )
            clickOnItemInList(index = 0)
        }

        val assetInfoPage = AssetInfoPage()

        with(assetInfoPage) {
            checkIsVisible()
            checkAssetInfoState(
                symbol = "BTC",
                name = "Bitcoin",
                rank = "1",
                supply = "17193925.0000000000000000",
                maxSupply = "21000000.0000000000000000",
                marketCapUsd = "119179791817.6740161068269075",
                volumeUsd24Hr = "2928356777.6066665425687196",
                priceUsd = "6931.5058555666618359",
                changePercent24Hr = "-0.8101417214350335",
                vwap24Hr = "7175.0663247679233209"
            )

            clickHistoryButton()
            checkIsNotVisible()
        }

        val assetHistoryPage = AssetHistoryPage()

        with(assetHistoryPage) {
            checkIsVisible()
            checkAssetHistoryListState(
                assetHistory = listOf(
                    Pair("6379.3997635993342453", "1530403200000"),
                    Pair("5249.2897635663782442", "1340203100000"),
                    Pair("6379.6558636794542412", "1420703400000")
                )
            )

            swipeToRefresh()
            checkErrorState(message = "No connection. Please try again!")

            clickTryAgainButton()
        }

        assetInfoPage.checkIsNotVisible()

        with(assetHistoryPage) {
            checkIsVisible()
            checkAssetHistoryListState(
                assetHistory = listOf(
                    Pair("6379.3997635993342453", "1530403200000"),
                    Pair("5249.2897635663782442", "1340203100000"),
                    Pair("6379.6558636794542412", "1420703400000")
                )
            )
        }

        pressBack()
        assetHistoryPage.checkIsNotVisible()

        with(assetInfoPage) {
            checkIsVisible()
            checkErrorState(message = "No connection. Please try again!")

            clickTryAgainButton()
        }

        assetHistoryPage.checkIsNotVisible()

        with(assetInfoPage) {
            checkIsVisible()
            checkAssetInfoState(
                symbol = "BTC",
                name = "Bitcoin",
                rank = "1",
                supply = "17193925.0000000000000000",
                maxSupply = "21000000.0000000000000000",
                marketCapUsd = "119179791817.6740161068269075",
                volumeUsd24Hr = "2928356777.6066665425687196",
                priceUsd = "6931.5058555666618359",
                changePercent24Hr = "-0.8101417214350335",
                vwap24Hr = "7175.0663247679233209"
            )
        }
    }
}