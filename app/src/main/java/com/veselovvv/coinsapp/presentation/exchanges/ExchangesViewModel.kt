package com.veselovvv.coinsapp.presentation.exchanges

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.veselovvv.coinsapp.core.Save
import com.veselovvv.coinsapp.di.core.CoreDomainModule
import com.veselovvv.coinsapp.domain.exchanges.ExchangesDomainToUiMapper
import com.veselovvv.coinsapp.domain.exchanges.FetchExchangesUseCase
import com.veselovvv.coinsapp.domain.exchanges.SearchExchangesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ExchangesViewModel @Inject constructor(
    private val communication: ExchangesCommunication,
    @CoreDomainModule.IODispatcher private val dispatchersIO: CoroutineDispatcher,
    @CoreDomainModule.MainDispatcher private val dispatchersUi: CoroutineDispatcher,
    private val fetchExchangesUseCase: FetchExchangesUseCase,
    private val searchExchangesUseCase: SearchExchangesUseCase,
    private val mapper: ExchangesDomainToUiMapper,
    private val exchangeCache: Save<Triple<String, String, String>>
) : ViewModel() {
    fun fetchExchanges() {
        communication.map(listOf(ExchangeUi.Progress))
        viewModelScope.launch(dispatchersIO) {
            val resultDomain = fetchExchangesUseCase.execute()
            val resultUi = resultDomain.map(mapper)
            withContext(dispatchersUi) {
                resultUi.map(communication)
            }
        }
    }

    fun searchExchanges(query: String) {
        communication.map(listOf(ExchangeUi.Progress))
        viewModelScope.launch(dispatchersIO) {
            val resultDomain = searchExchangesUseCase.execute(query)
            val resultUi = resultDomain.map(mapper)
            withContext(dispatchersUi) {
                resultUi.map(communication)
            }
        }
    }

    fun saveExchangeInfo(id: String, name: String, rank: String) =
        exchangeCache.save(Triple(id, name, rank))

    fun observe(owner: LifecycleOwner, observer: Observer<List<ExchangeUi>>) =
        communication.observe(owner, observer)
}