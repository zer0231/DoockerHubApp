package com.zero.doockerhubapp.fragments.loginFragment.repository

import com.google.gson.JsonObject
import com.zero.doockerhubapp.utils.APIResponse
import com.zero.doockerhubapp.utils.NetworkResult
import com.zero.doockerhubapp.utils.RemoteDataSource
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

@ActivityRetainedScoped
class LoginRepository @Inject constructor(private val remoteDataSource: RemoteDataSource):
    APIResponse() {
    suspend fun getLoginResponse(username: String, password: String): Flow<NetworkResult<JsonObject>> {
        return flow {
            emit(safeApiCall { remoteDataSource.login(username, password) }!!)
        }.flowOn(Dispatchers.IO)
    }
}