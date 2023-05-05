package com.zero.doockerhubapp.utils

import com.zero.doockerhubapp.utils.Constants.Companion.ACCESS_TOKEN
import com.zero.doockerhubapp.utils.Constants.Companion.LOGIN
import retrofit2.Response
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface API {

    @FormUrlEncoded
    @POST(LOGIN)
    suspend fun postLogin(): Response<String>

    @POST(ACCESS_TOKEN)
    suspend fun postFetchToken(): Response<String>




}