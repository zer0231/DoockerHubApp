package com.zero.doockerhubapp.fragments.dashboardFragment.repository

import android.util.Log
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.zero.doockerhubapp.fragments.dashboardFragment.model.RepoSummary
import com.zero.doockerhubapp.utils.APIResponse
import com.zero.doockerhubapp.utils.NetworkResult
import com.zero.doockerhubapp.utils.PersonalTokenScope
import com.zero.doockerhubapp.utils.RemoteDataSource
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

@ActivityRetainedScoped
class DashboardRepository @Inject constructor(private val remoteDataSource: RemoteDataSource) :
    APIResponse() {

    suspend fun getPersonalToken(
        tokenLabel: String,
        scope: String
    ): Flow<NetworkResult<JsonObject>> {
        val requestBody = JsonObject()
        val jsonArray = JsonArray()
        requestBody.addProperty("token_label", tokenLabel)
        jsonArray.add(scope)
        requestBody.add("scopes", jsonArray)
        return flow {
            emit(safeApiCall { remoteDataSource.postPersonalToken(requestBody) })
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getImageSummary():Flow<NetworkResult<RepoSummary>>{
        return flow {
            emit(safeApiCall { remoteDataSource.getImageSummary() })
        }.flowOn(Dispatchers.IO)
    }


}