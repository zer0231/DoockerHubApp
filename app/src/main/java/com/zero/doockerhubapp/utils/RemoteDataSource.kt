package com.zero.doockerhubapp.utils

import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val apiService: APIService) {
    suspend fun getToken() = apiService.postFetchToken()

    suspend fun login(username:String,password:String) = apiService.postLogin(username, password)
}
