package com.zero.doockerhubapp.utils.di

import android.content.Context
import android.util.Log
import com.zero.doockerhubapp.utils.APIService
import com.zero.doockerhubapp.utils.Constants.Companion.BASE_URL
import com.zero.doockerhubapp.utils.sharedPreferenceManager.SharedPreferenceUtil
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

const val TIMEOUT_IN_SECOND: Long = 15

@Module
@InstallIn(SingletonComponent::class)
object RetrofitClient {


    @Singleton
    @Provides
    fun provideConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()


    @Provides
    fun provideUserSharedPreference(@ApplicationContext context: Context) =
        SharedPreferenceUtil(context)


//    @Provides
//    fun provideTokenInterceptor(userSharedPreference: SharedPreferenceUtil): Interceptor {
//        return Interceptor { chain ->
//            val token = userSharedPreference.getUserToken()
//            Log.d("TOKEN", token.toString())
//
//            val request = chain.request().newBuilder()
//                .addHeader("User-Agent","PostmanRuntime/7.32.2")
//                .addHeader("Content-type","application/json")
//                .addHeader("Accept","*/*")
//                .addHeader("Connection","keep-alive")
//                .addHeader("Authorization", "Bearer ${token.toString()}").build()
//            chain.proceed(request)
//        }
//    }


    @Singleton
    @Provides
    fun provideHttpClient(tokenInterceptor: TokenInterceptor): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(tokenInterceptor)
            .readTimeout(TIMEOUT_IN_SECOND, TimeUnit.SECONDS)
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
    fun provideApiService(retrofit: Retrofit): APIService = retrofit.create(APIService::class.java)
}