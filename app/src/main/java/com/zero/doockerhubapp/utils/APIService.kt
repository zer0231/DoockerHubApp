package com.zero.doockerhubapp.utils

import com.google.gson.JsonObject
import com.zero.doockerhubapp.utils.Constants.Companion.ACCESS_TOKEN
import com.zero.doockerhubapp.utils.Constants.Companion.LOGIN
import retrofit2.Response
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface APIService {

    @FormUrlEncoded
    @POST(LOGIN)
    suspend fun postLogin(username:String,password:String): Response<JsonObject>

    @POST(ACCESS_TOKEN)
    suspend fun postFetchToken(): Response<JsonObject>




}