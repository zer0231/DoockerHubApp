package com.zero.doockerhubapp.fragments.loginFragment.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.JsonObject
import com.zero.doockerhubapp.fragments.loginFragment.repository.LoginRepository
import com.zero.doockerhubapp.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: LoginRepository
):ViewModel() {

    private val _loginResponse: MutableLiveData<NetworkResult<JsonObject>> = MutableLiveData()
    val loginResponse: LiveData<NetworkResult<JsonObject>> = _loginResponse

    fun fetchLoginResponse(username:String,password:String){
        viewModelScope.launch(Dispatchers.IO ) {
            _loginResponse.postValue( NetworkResult.Loading())
            repository.getLoginResponse(username,password).collect{
                _loginResponse.postValue(it)
            }
        }
    }
}
