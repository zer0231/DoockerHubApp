package com.zero.doockerhubapp.utils

import com.google.gson.JsonObject
import com.zero.doockerhubapp.utils.Constants.Companion.IMAGE_DETAIL
import com.zero.doockerhubapp.utils.Constants.Companion.LOGIN
import com.zero.doockerhubapp.utils.Constants.Companion.NAMESPACES
import com.zero.doockerhubapp.utils.Constants.Companion.PERSONAL_ACCESS_TOKEN
import com.zero.doockerhubapp.utils.Constants.Companion.REPO_DETAIL
import com.zero.doockerhubapp.utils.sharedPreferenceManager.SharedPreferenceUtil
import javax.inject.Inject

class RemoteDataSource @Inject constructor(

    private val userSharedPreference:SharedPreferenceUtil,
    private val apiService: APIService) {
    suspend fun postPersonalToken(requestBody:JsonObject) = apiService.postPersonalToken(PERSONAL_ACCESS_TOKEN,requestBody)

    suspend fun postLogin(username: String, password: String) =
        apiService.postLogin(LOGIN, username, password)

    suspend fun getImageSummary() = apiService.getImageSummary(NAMESPACES+userSharedPreference.getUserName()+ REPO_DETAIL)

    suspend fun getImageDetail(imageName:String) = apiService.getImageDetail(NAMESPACES+userSharedPreference.getUserName()+ REPO_DETAIL+imageName+ IMAGE_DETAIL) //requires pro >:|
}
