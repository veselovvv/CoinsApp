package com.veselovvv.coinsapp.core

import com.veselovvv.coinsapp.R
import retrofit2.HttpException
import java.net.UnknownHostException

interface Mapper {
    interface DataToDomain<S, R> {
        fun map(e: Exception): R

        abstract class Base<S, R> : DataToDomain<S, R> {
            protected fun getErrorType(e: Exception) = when (e) {
                is UnknownHostException -> ErrorType.NO_CONNECTION
                is HttpException -> ErrorType.SERVICE_UNAVAILABLE
                else -> ErrorType.GENERIC_ERROR
            }
        }
    }

    interface DomainToUi<S, T> {
        fun map(errorType: ErrorType): T

        abstract class Base<S, T>(
            private val resourceProvider: ResourceProvider
        ) : DomainToUi<S, T> {
            fun getErrorMessage(errorType: ErrorType) = resourceProvider.getString(
                when (errorType) {
                    ErrorType.NO_CONNECTION -> R.string.no_connection_message
                    ErrorType.SERVICE_UNAVAILABLE -> R.string.service_unavailable_message
                    else -> R.string.something_went_wrong
                }
            )
        }
    }
}