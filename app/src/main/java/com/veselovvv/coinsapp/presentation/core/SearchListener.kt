package com.veselovvv.coinsapp.presentation.core

import androidx.appcompat.widget.SearchView

abstract class SearchListener : SearchView.OnQueryTextListener {
    override fun onQueryTextSubmit(query: String?) = find(query)
    override fun onQueryTextChange(newText: String?) = find(newText)

    abstract fun find(query: String?): Boolean
}