package com.veselovvv.coinsapp.presentation.markets

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.veselovvv.coinsapp.di.core.CoreDomainModule
import com.veselovvv.coinsapp.domain.markets.FetchMarketsUseCase
import com.veselovvv.coinsapp.domain.markets.MarketsDomainToUiMapper
import com.veselovvv.coinsapp.domain.markets.SearchMarketsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MarketsViewModel @Inject constructor(
    private val communication: MarketsCommunication,
    @CoreDomainModule.IODispatcher private val dispatchersIO: CoroutineDispatcher,
    @CoreDomainModule.MainDispatcher private val dispatchersUi: CoroutineDispatcher,
    private val fetchMarketsUseCase: FetchMarketsUseCase,
    private val searchMarketsUseCase: SearchMarketsUseCase,
    private val mapper: MarketsDomainToUiMapper
) : ViewModel() {
    fun fetchMarkets() {
        communication.map(listOf(MarketUi.Progress))
        viewModelScope.launch(dispatchersIO) {
            val resultDomain = fetchMarketsUseCase.execute()
            val resultUi = resultDomain.map(mapper)
            withContext(dispatchersUi) {
                resultUi.map(communication)
            }
        }
    }

    fun searchMarkets(query: String) {
        communication.map(listOf(MarketUi.Progress))
        viewModelScope.launch(dispatchersIO) {
            val resultDomain = searchMarketsUseCase.execute(query)
            val resultUi = resultDomain.map(mapper)
            withContext(dispatchersUi) {
                resultUi.map(communication)
            }
        }
    }

    fun observe(owner: LifecycleOwner, observer: Observer<List<MarketUi>>) =
        communication.observe(owner, observer)
}