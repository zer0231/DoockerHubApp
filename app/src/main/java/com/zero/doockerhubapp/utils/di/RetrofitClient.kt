package com.zero.doockerhubapp.utils.di

import android.content.Context
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

    @Singleton
    @Provides
    fun provideTokenInterceptor(userSharedPreference: SharedPreferenceUtil): Interceptor =
        Interceptor { chain ->
            val token = userSharedPreference.getUserToken()
//            "eyJ0eXAiOiJKV1QiLCJ4NWMiOlsiTUlJQytUQ0NBcCtnQXdJQkFnSUJBREFLQmdncWhrak9QUVFEQWpCR01VUXdRZ1lEVlFRREV6dFNUVWxHT2xGTVJqUTZRMGRRTXpwUk1rVmFPbEZJUkVJNlZFZEZWVHBWU0ZWTU9rWk1WalE2UjBkV1dqcEJOVlJIT2xSTE5GTTZVVXhJU1RBZUZ3MHlNekF4TURZd05ESTNORFJhRncweU5EQXhNall3TkRJM05EUmFNRVl4UkRCQ0JnTlZCQU1UTzBORFZVWTZTalZITmpwRlExTkVPa05XUkVjNlZEZE1NanBLV2taUk9sTk5NMFE2V0ZsUE5EcFdOME5IT2tkR1YwSTZXbG8xTmpvMFZVUkRNSUlCSWpBTkJna3Foa2lHOXcwQkFRRUZBQU9DQVE4QU1JSUJDZ0tDQVFFQXpOMGIwajdVeVwvYXNqWVhXaDJnM3FvNkpoT2tBalhXQVVCY3NIdTZoWVpSRkw5dmU4MTNUQjRjbDhRa3hDSTRjVWdHR25HV1hWeEgydTV2RXR4U09xV0JyeFNOcmhTTWpcL1ZPKzZcL2lZKzhtRkZhMEdieXMxd1Q1YzZWOXFkTmhEYlRjcEF1WEoxUjRiS3UrdVRqVUtFSGF5akhSOUxQRHlHZ1BcL25tQVp2Tk9YR21yU1NKRkk2eEU2ZjdBXC8rOVptcWgyVlRaQlc0cXduSnF0cnNJM2NveDNQczMwS2MrYUh3V3VZdk5RdFNBdytqVXhDVVFoRWZGa0lKSzh6OVdsXC9RY3RPRHBHVHlzbVRwYzc2WlRHSllrZ2hoRkxSRDJiUE5RRDhFNWVnSmtkUDl4aWlubFRsdzIwcVpYVUZpanVhQXJ3TkdMSW1CRFhNMFpSNWM1bVN2d0lEQVFBQm80R3lNSUd2TUE0R0ExVWREd0VCXC93UUVBd0lIZ0RBUEJnTlZIU1VFQ0RBR0JnUlZIU1VBTUVRR0ExVWREZ1E5QkR0RFExVkdPa28xUnpZNlJVTlRSRHBEVmtSSE9sUTNUREk2U2xwR1VUcFRUVE5FT2xoWlR6UTZWamREUnpwSFJsZENPbHBhTlRZNk5GVkVRekJHQmdOVkhTTUVQekE5Z0R0U1RVbEdPbEZNUmpRNlEwZFFNenBSTWtWYU9sRklSRUk2VkVkRlZUcFZTRlZNT2taTVZqUTZSMGRXV2pwQk5WUkhPbFJMTkZNNlVVeElTVEFLQmdncWhrak9QUVFEQWdOSUFEQkZBaUVBbVE0eGxBdldWUCtPXC9hNlhDU05pYUFYRU1Bb1RQVFRYRWJYMks2RVU4ZTBDSUg0QTAwSVhtUndjdGtEOHlYNzdkTVoyK0pEY1FGdDFxRktMZFR5SnVzT1UiXSwiYWxnIjoiUlMyNTYifQ.eyJpc3MiOiJodHRwczpcL1wvYXBpLmRvY2tlci5jb21cLyIsImF1ZCI6WyJodHRwczpcL1wvaHViLmRvY2tlci5jb20iXSwianRpIjoiN2MyZDgxMTQtOWI4Yy00MWFlLWIwZmMtYmNlYjhjNTgwNjk4IiwidXNlcl9pZCI6IjYyZmU1OWFhNjg0YTQ5ZTVhNzExY2MxMzUyNGJhZDY4IiwidXNlcm5hbWUiOiJ6ZXIwMjMxIiwiaWF0IjoxNjgzMjYzMTg4LCJzdWIiOiI2MmZlNTlhYTY4NGE0OWU1YTcxMWNjMTM1MjRiYWQ2OCIsInNvdXJjZSI6eyJpZCI6IjYyZmU1OWFhLTY4NGEtNDllNS1hNzExLWNjMTM1MjRiYWQ2OCIsInR5cGUiOiJwd2QifSwiZXhwIjoxNjg1ODU1MTg4LCJodHRwczpcL1wvaHViLmRvY2tlci5jb20iOnsicm9sZXMiOltdLCJlbWFpbCI6InByYW5heV96ZXIwQHByb3Rvbm1haWwuY29tIiwic291cmNlIjoiZG9ja2VyX3B3ZHw2MmZlNTlhYS02ODRhLTQ5ZTUtYTcxMS1jYzEzNTI0YmFkNjgiLCJ1dWlkIjoiNjJmZTU5YWEtNjg0YS00OWU1LWE3MTEtY2MxMzUyNGJhZDY4IiwidXNlcm5hbWUiOiJ6ZXIwMjMxIiwic2Vzc2lvbl9pZCI6IjdjMmQ4MTE0LTliOGMtNDFhZS1iMGZjLWJjZWI4YzU4MDY5OCJ9LCJzZXNzaW9uX2lkIjoiN2MyZDgxMTQtOWI4Yy00MWFlLWIwZmMtYmNlYjhjNTgwNjk4In0.hYU7Y3ETC3qxtlgAOUq1_Amqd2U3kEBbyeknBlQR3l1nv7vGssVLW_tFef3y244uWlqop2iKPtL6VJ65pThpyYiq65xudW6RUm3oNrLH8TBX4faB16Ja2Au5tKKOn8oYqdlPkdOc4eazNyyRWrcOco93oEEv1TL2WEFIGsf6e2GPiLSlqnOnpIAMuGTEutJ4bCxechhSCb1lrRTeMiy-mf1lD72URc2EScj7TMZWKqPsj5lgWo0LiaXeXhAAW-9msJvhYStT1jjL76wtw5EvxUFfSpHgkIqwzgoDGv4Cd0tZiMEKZsRube1YV_al5CRbnPIDFdFKlwp1y8YlqECdNg"
            chain.proceed(
                chain.request().newBuilder().header("Authorization", "bearer $token").build()
            )
        }

    @Singleton
    @Provides
    fun provideHttpClient(tokenInterceptor: Interceptor): OkHttpClient {
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