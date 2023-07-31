package com.veselovvv.coinsapp.presentation.assetinfo

import android.view.ViewGroup
import com.google.android.material.button.MaterialButton
import com.google.android.material.textview.MaterialTextView
import com.veselovvv.coinsapp.core.Retry
import com.veselovvv.coinsapp.presentation.core.hide
import com.veselovvv.coinsapp.presentation.core.show

sealed class AssetInfoUi {
    open fun setup(progressLayout: ViewGroup) = progressLayout.hide()

    open fun map(
        supplyTextView: MaterialTextView,
        maxSupplyTextView: MaterialTextView,
        marketCapUsdTextView: MaterialTextView,
        volumeUsd24HrTextView: MaterialTextView,
        priceUsdTextView: MaterialTextView,
        changePercent24HrTextView: MaterialTextView,
        vwap24HrTextView: MaterialTextView
    ) = Unit

    open fun map(
        failLayout: ViewGroup,
        messageTextView: MaterialTextView,
        tryAgainButton: MaterialButton,
        retry: Retry
    ) = Unit

    object Progress : AssetInfoUi() {
        override fun setup(progressLayout: ViewGroup) = progressLayout.show()
    }

    data class Base(
        private val supply: String,
        private val maxSupply: String,
        private val marketCapUsd: String,
        private val volumeUsd24Hr: String,
        private val priceUsd: String,
        private val changePercent24Hr: String,
        private val vwap24Hr: String
    ) : AssetInfoUi() {
        override fun map(
            supplyTextView: MaterialTextView,
            maxSupplyTextView: MaterialTextView,
            marketCapUsdTextView: MaterialTextView,
            volumeUsd24HrTextView: MaterialTextView,
            priceUsdTextView: MaterialTextView,
            changePercent24HrTextView: MaterialTextView,
            vwap24HrTextView: MaterialTextView
        ) {
            supplyTextView.text = supply
            maxSupplyTextView.text = maxSupply
            marketCapUsdTextView.text = marketCapUsd
            volumeUsd24HrTextView.text = volumeUsd24Hr
            priceUsdTextView.text = priceUsd
            changePercent24HrTextView.text = changePercent24Hr
            vwap24HrTextView.text = vwap24Hr
        }
    }

    data class Fail(private val errorMessage: String) : AssetInfoUi() {
        override fun map(
            failLayout: ViewGroup,
            messageTextView: MaterialTextView,
            tryAgainButton: MaterialButton,
            retry: Retry
        ) {
            failLayout.show()
            messageTextView.text = errorMessage
            tryAgainButton.setOnClickListener {
                retry.tryAgain()
                failLayout.hide()
            }
        }
    }
}
