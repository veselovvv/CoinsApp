package com.veselovvv.coinsapp.core

interface Object<T, M> {
    fun map(mapper: M): T
}