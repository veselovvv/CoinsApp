package com.veselovvv.coinsapp.presentation.assets

data class AssetParameters(
    private val id: String,
    private val rank: String,
    private val symbol: String,
    private val name: String
) {
    fun getId() = id
    fun getRank() = rank
    fun getSymbol() = symbol
    fun getName() = name
}
