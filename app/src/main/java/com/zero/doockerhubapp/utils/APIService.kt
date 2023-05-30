package com.zero.doockerhubapp.utils

import com.google.gson.JsonObject
import com.zero.doockerhubapp.fragments.dashboardFragment.model.RepoSummary
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Url

interface APIService {

    @FormUrlEncoded
    @POST
    suspend fun postLogin(
        @Url url: String, @Field("username") username: String, @Field("password") password: String
    ): Response<JsonObject>


    @POST
    suspend fun postPersonalToken(
        @Url url: String, @Body requestObject: JsonObject
    ): Response<JsonObject>

    @GET
    suspend fun getImageSummary(@Url url: String): Response<RepoSummary>

    @GET
    suspend fun getImageDetail(@Url url:String):Response<JsonObject>


}