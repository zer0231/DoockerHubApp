package com.zero.doockerhubapp.utils

import retrofit2.Response

abstract class APIResponse {
    suspend fun <T> safeApiCall(apiCall: suspend () -> Response<T>): NetworkResult<T>? {
        try {
            val response = apiCall()
            if (response.isSuccessful) {
                val body = response.body()
                body?.let { return NetworkResult.Success(body) }
            }else{
                return NetworkResult.Error("${response.code()}, ${response.message()}")
            }

        } catch (e: Exception) {
            return  NetworkResult.Error(e.message.toString())
        }
        return NetworkResult.Loading()
    }
}
