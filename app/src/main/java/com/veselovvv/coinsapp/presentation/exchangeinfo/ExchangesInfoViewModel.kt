package com.veselovvv.coinsapp.presentation.exchangeinfo

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.veselovvv.coinsapp.core.Read
import com.veselovvv.coinsapp.di.core.CoreDomainModule
import com.veselovvv.coinsapp.domain.exchangeinfo.ExchangesInfoDomainToUiMapper
import com.veselovvv.coinsapp.domain.exchangeinfo.FetchExchangeInfoUseCase
import com.veselovvv.coinsapp.presentation.exchanges.ExchangesParameters
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ExchangesInfoViewModel @Inject constructor(
    private val communication: ExchangesInfoCommunication,
    @CoreDomainModule.IODispatcher private val dispatchersIO: CoroutineDispatcher,
    @CoreDomainModule.MainDispatcher private val dispatchersUi: CoroutineDispatcher,
    private val fetchExchangeInfoUseCase: FetchExchangeInfoUseCase,
    private val mapper: ExchangesInfoDomainToUiMapper,
    private val exchangeCache: Read<ExchangesParameters>
) : ViewModel() {
    fun fetchExchangeInfo(id: String) {
        communication.map(ExchangeInfoUi.Progress)
        viewModelScope.launch(dispatchersIO) {
            val resultDomain = fetchExchangeInfoUseCase.execute(id)
            val resultUi = resultDomain.map(mapper)
            withContext(dispatchersUi) {
                resultUi.map(communication)
            }
        }
    }

    fun observe(owner: LifecycleOwner, observer: Observer<ExchangeInfoUi>) =
        communication.observe(owner, observer)

    fun getExchangeId() = exchangeCache.read().getId()
    fun getExchangeName() = exchangeCache.read().getName()
    fun getExchangeRank() = exchangeCache.read().getRank()
}