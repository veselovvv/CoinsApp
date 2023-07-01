package com.veselovvv.coinsapp.core

import android.content.Context
import androidx.annotation.StringRes

interface ResourceProvider {
    fun getString(@StringRes id: Int): String

    //TODO add base implementation
}