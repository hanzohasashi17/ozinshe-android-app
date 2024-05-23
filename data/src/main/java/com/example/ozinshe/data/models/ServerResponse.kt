package com.example.ozinshe.data.models

sealed class ServerResponse<out T : Any> {
    data class Success<out T : Any>(val data: T) : ServerResponse<T>()
    data class Error(val exception: Exception) : ServerResponse<Nothing>()
}