package com.zero.doockerhubapp.utils

import retrofit2.Response

abstract class APIResponse {
    suspend fun <T> safeApiCall(apiCall: suspend () -> Response<T>): NetworkResult<T> {
        try {
            val response = apiCall()
            if (response.isSuccessful) {
                val body = response.body()
                body?.let { return NetworkResult.Success(body) }
            }else{
                return NetworkResult.Error(message = "$response.message()", code = response.code())
            }

        } catch (e: Exception) {
            return  NetworkResult.Error(message = e.message.toString(), code = 999) //CODE 999 means error while creating request
        }
        return NetworkResult.Loading()
    }
}
