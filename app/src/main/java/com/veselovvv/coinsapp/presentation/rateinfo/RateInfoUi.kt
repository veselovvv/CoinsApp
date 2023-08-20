package com.veselovvv.coinsapp.presentation.rateinfo

import android.view.ViewGroup
import com.google.android.material.button.MaterialButton
import com.google.android.material.textview.MaterialTextView
import com.veselovvv.coinsapp.core.Retry
import com.veselovvv.coinsapp.presentation.core.hide
import com.veselovvv.coinsapp.presentation.core.show

sealed class RateInfoUi {
    open fun setup(progressLayout: ViewGroup) = progressLayout.hide()

    open fun map(
        currencySymbolTextView: MaterialTextView,
        typeTextView: MaterialTextView
    ) = Unit

    open fun map(
        failLayout: ViewGroup,
        messageTextView: MaterialTextView,
        tryAgainButton: MaterialButton,
        retry: Retry
    ) = Unit

    object Progress : RateInfoUi() {
        override fun setup(progressLayout: ViewGroup) = progressLayout.show()
    }

    data class Base(
        private val currencySymbol: String,
        private val type: String
    ) : RateInfoUi() {
        override fun map(currencySymbolTextView: MaterialTextView, typeTextView: MaterialTextView) {
            currencySymbolTextView.text = currencySymbol
            typeTextView.text = type
        }
    }

    data class Fail(private val errorMessage: String) : RateInfoUi() {
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
