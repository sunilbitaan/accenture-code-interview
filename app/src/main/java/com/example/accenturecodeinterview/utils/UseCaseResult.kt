package com.example.accenturecodeinterview.utils

/**
 * Create By Sunil
 */
sealed class UseCaseResult<out T : Any> {
    class Success<out T : Any>(val data: T) : UseCaseResult<T>()

    class Error(val exception: Throwable) : UseCaseResult<Nothing>()

}