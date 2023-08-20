package com.veselovvv.coinsapp.presentation.rateinfo

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.veselovvv.coinsapp.core.Read
import com.veselovvv.coinsapp.di.core.CoreDomainModule
import com.veselovvv.coinsapp.domain.rateinfo.FetchRateInfoUseCase
import com.veselovvv.coinsapp.domain.rateinfo.RatesInfoDomainToUiMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class RatesInfoViewModel @Inject constructor(
    private val communication: RatesInfoCommunication,
    @CoreDomainModule.IODispatcher private val dispatchersIO: CoroutineDispatcher,
    @CoreDomainModule.MainDispatcher private val dispatchersUi: CoroutineDispatcher,
    private val fetchRateInfoUseCase: FetchRateInfoUseCase,
    private val mapper: RatesInfoDomainToUiMapper,
    private val rateCache: Read<Triple<String, String, String>>
) : ViewModel() {
    fun fetchRateInfo(id: String) {
        communication.map(RateInfoUi.Progress)
        viewModelScope.launch(dispatchersIO) {
            val resultDomain = fetchRateInfoUseCase.execute(id)
            val resultUi = resultDomain.map(mapper)
            withContext(dispatchersUi) {
                resultUi.map(communication)
            }
        }
    }

    fun observe(owner: LifecycleOwner, observer: Observer<RateInfoUi>) =
        communication.observe(owner, observer)

    fun getRateId() = rateCache.read().first
    fun getRateSymbol() = rateCache.read().second
    fun getRateRateUsd() = rateCache.read().third
}