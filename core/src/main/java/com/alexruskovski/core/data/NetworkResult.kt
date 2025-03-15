package com.alexruskovski.core.data

public sealed interface NetworkResult<out R> {
    data class Success<R>(val data: R) : NetworkResult<R>
    data class Error(val exception: Throwable) : NetworkResult<Nothing>
    data object Loading : NetworkResult<Nothing>
}
