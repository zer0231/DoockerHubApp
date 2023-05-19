package com.zero.doockerhubapp.utils

import com.google.gson.internal.ObjectConstructor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class TokenInterceptor() :


Interceptor {
    @Singleton
    @Provides
    override fun intercept(chain: Interceptor.Chain): Response {

        var request = chain.request()
        request = request.newBuilder().header("Authorization", "bearer 123").build()
        return chain.proceed(request)
    }

    init {
        val type:String
        val token:String
    }

}