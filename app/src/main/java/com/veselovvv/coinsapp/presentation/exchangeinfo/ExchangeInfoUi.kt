package com.veselovvv.coinsapp.presentation.exchangeinfo

import android.view.ViewGroup
import com.google.android.material.button.MaterialButton
import com.google.android.material.textview.MaterialTextView
import com.veselovvv.coinsapp.core.Retry
import com.veselovvv.coinsapp.presentation.core.hide
import com.veselovvv.coinsapp.presentation.core.show

sealed class ExchangeInfoUi {
    open fun setup(progressLayout: ViewGroup) = progressLayout.hide()

    open fun map(
        percentTotalVolumeTextView: MaterialTextView,
        volumeUsdTextView: MaterialTextView,
        tradingPairsTextView: MaterialTextView,
        exchangeUrlTextView: MaterialTextView
    ) = Unit

    open fun map(
        failLayout: ViewGroup,
        messageTextView: MaterialTextView,
        tryAgainButton: MaterialButton,
        retry: Retry
    ) = Unit

    object Progress : ExchangeInfoUi() {
        override fun setup(progressLayout: ViewGroup) = progressLayout.show()
    }

    data class Base(
        private val percentTotalVolume: String,
        private val volumeUsd: String,
        private val tradingPairs: String,
        private val exchangeUrl: String
    ) : ExchangeInfoUi() {
        override fun map(
            percentTotalVolumeTextView: MaterialTextView,
            volumeUsdTextView: MaterialTextView,
            tradingPairsTextView: MaterialTextView,
            exchangeUrlTextView: MaterialTextView
        ) {
            percentTotalVolumeTextView.text = percentTotalVolume
            volumeUsdTextView.text = volumeUsd
            tradingPairsTextView.text = tradingPairs
            exchangeUrlTextView.text = exchangeUrl
        }
    }

    data class Fail(private val errorMessage: String) : ExchangeInfoUi() {
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
