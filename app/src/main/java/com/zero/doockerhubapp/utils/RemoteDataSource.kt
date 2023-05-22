package com.zero.doockerhubapp.utils

import com.zero.doockerhubapp.utils.Constants.Companion.ACCESS_TOKEN
import com.zero.doockerhubapp.utils.Constants.Companion.LOGIN
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val apiService: APIService) {
    suspend fun getToken() = apiService.postFetchToken(ACCESS_TOKEN)

    suspend fun postLogin(username: String, password: String) =
        apiService.postLogin(LOGIN, username, password)
}
