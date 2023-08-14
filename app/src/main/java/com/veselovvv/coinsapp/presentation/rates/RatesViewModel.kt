package com.veselovvv.coinsapp.presentation.rates

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.veselovvv.coinsapp.core.Save
import com.veselovvv.coinsapp.di.core.CoreDomainModule
import com.veselovvv.coinsapp.domain.rates.FetchRatesUseCase
import com.veselovvv.coinsapp.domain.rates.RatesDomainToUiMapper
import com.veselovvv.coinsapp.domain.rates.SearchRatesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class RatesViewModel @Inject constructor(
    private val communication: RatesCommunication,
    @CoreDomainModule.IODispatcher private val dispatchersIO: CoroutineDispatcher,
    @CoreDomainModule.MainDispatcher private val dispatchersUi: CoroutineDispatcher,
    private val fetchRatesUseCase: FetchRatesUseCase,
    private val searchRatesUseCase: SearchRatesUseCase,
    private val mapper: RatesDomainToUiMapper,
    private val rateCache: Save<Triple<String, String, String>>
) : ViewModel() {
    fun fetchRates() {
        communication.map(listOf(RateUi.Progress))
        viewModelScope.launch(dispatchersIO) {
            val resultDomain = fetchRatesUseCase.execute()
            val resultUi = resultDomain.map(mapper)
            withContext(dispatchersUi) {
                resultUi.map(communication)
            }
        }
    }

    fun searchRates(query: String) {
        communication.map(listOf(RateUi.Progress))
        viewModelScope.launch(dispatchersIO) {
            val resultDomain = searchRatesUseCase.execute(query)
            val resultUi = resultDomain.map(mapper)
            withContext(dispatchersUi) {
                resultUi.map(communication)
            }
        }
    }

    fun saveRateInfo(id: String, symbol: String, rateUsd: String) =
        rateCache.save(Triple(id, symbol, rateUsd))

    fun observe(owner: LifecycleOwner, observer: Observer<List<RateUi>>) =
        communication.observe(owner, observer)
}