package com.zero.doockerhubapp.fragments.loginFragment.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.gson.JsonObject
import com.zero.doockerhubapp.fragments.loginFragment.repository.LoginRepository
import com.zero.doockerhubapp.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: LoginRepository,
    application: Application
) : AndroidViewModel(application) {

    private val _loginResponse: MutableLiveData<NetworkResult<JsonObject>> = MutableLiveData()
    val loginResponse: LiveData<NetworkResult<JsonObject>> = _loginResponse

    fun fetchLoginResponse(username: String, password: String) = viewModelScope.launch {
        _loginResponse.value = NetworkResult.Loading()
        repository.getLoginResponse(username, password).collect { login ->
            _loginResponse.value = login
        }
    }
}
