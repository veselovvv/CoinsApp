package com.veselovvv.coinsapp.core

interface Object<T, M : Mapper> {
    fun map(mapper: M): T
}