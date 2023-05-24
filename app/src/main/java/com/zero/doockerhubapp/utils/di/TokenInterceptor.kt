package com.zero.doockerhubapp.utils.di

import android.util.Log
import com.zero.doockerhubapp.utils.sharedPreferenceManager.SharedPreferenceUtil
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class TokenInterceptor @Inject constructor(var userSharedPreference: SharedPreferenceUtil) :
    Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val token = userSharedPreference.getUserToken()
        Log.d("TOKEN123", token.toString())

        val modifiedRequest = originalRequest.newBuilder()
            .header("Authorization", "Bearer $token")
            .addHeader("User-Agent", "PostmanRuntime/7.32.2")
            .addHeader("Content-type", "application/json")
            .addHeader("Accept", "*/*")
            .addHeader("Connection", "keep-alive")
            .build()

        return chain.proceed(modifiedRequest)
    }
}