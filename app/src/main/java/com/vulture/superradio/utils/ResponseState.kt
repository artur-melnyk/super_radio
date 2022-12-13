package com.vulture.superradio.utils

sealed class ResponseState<T>(val data: T? = null, val exception: Exception? = null)

class Success<T>(data: T) : ResponseState<T>(data = data)
class DataError<T>(exception: Exception) : ResponseState<T>(exception = exception)
class Loading<T> : ResponseState<T>()