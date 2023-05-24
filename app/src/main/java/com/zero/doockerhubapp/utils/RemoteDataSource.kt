package com.zero.doockerhubapp.utils

import com.google.gson.JsonObject
import com.zero.doockerhubapp.utils.Constants.Companion.LOGIN
import com.zero.doockerhubapp.utils.Constants.Companion.PERSONAL_ACCESS_TOKEN
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val apiService: APIService) {
    suspend fun postPersonalToken(requestBody:JsonObject) = apiService.postPersonalToken(PERSONAL_ACCESS_TOKEN,requestBody)

    suspend fun postLogin(username: String, password: String) =
        apiService.postLogin(LOGIN, username, password)
}
