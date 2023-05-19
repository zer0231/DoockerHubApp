package com.zero.doockerhubapp.utils.di

import com.zero.doockerhubapp.utils.APIService
import com.zero.doockerhubapp.utils.Constants.Companion.BASE_URL
import com.zero.doockerhubapp.utils.TokenInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

const val TIMEOUT_IN_SECOND: Long = 15

@Module
@InstallIn(SingletonComponent::class)
object RetrofitClient {

    fun provideHttpClient(): OkHttpClient {
        val type = "Bearer"
        val token = "123"
        return OkHttpClient.Builder().addInterceptor(TokenInterceptor(type,token)).readTimeout(TIMEOUT_IN_SECOND, TimeUnit.SECONDS)
            .connectTimeout(TIMEOUT_IN_SECOND, TimeUnit.SECONDS).build()
    }

    @Singleton
    @Provides   //IN ORDER TO CREATE A RETROFIT INSTANCE IT IS DEPENDENT ON httpClient AND converterFactory SO HILT SHOULD KNOW TO CREATE THEM FIRST
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit {

        return Retrofit.Builder().baseUrl(BASE_URL).client(okHttpClient)
            .addConverterFactory(gsonConverterFactory).build()
    }


    @Singleton
    @Provides
    fun provideConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()


    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): APIService = retrofit.create(APIService::class.java)



}