package com.veselovvv.coinsapp.presentation.exchanges

data class ExchangesParameters(
    private val id: String,
    private val name: String,
    private val rank: String
) {
    fun getId() = id
    fun getName() = name
    fun getRank() = rank
}
