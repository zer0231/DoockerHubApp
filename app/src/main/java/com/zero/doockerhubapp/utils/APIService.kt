package com.zero.doockerhubapp.utils

import com.google.gson.JsonObject
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import retrofit2.http.Url

interface APIService {

    @FormUrlEncoded
    @POST
    suspend fun postLogin(
        @Url url: String,
        @Field("username") username: String,
        @Field("password") password: String
    ): Response<JsonObject>

    @POST
    suspend fun postFetchToken(@Url url: String): Response<JsonObject>


}