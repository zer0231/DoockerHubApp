package com.zero.doockerhubapp.fragments.dashboardFragment.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.gson.JsonObject
import com.zero.doockerhubapp.fragments.dashboardFragment.model.RepoSummary
import com.zero.doockerhubapp.fragments.dashboardFragment.repository.DashboardRepository
import com.zero.doockerhubapp.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val repository: DashboardRepository, application: Application
) : AndroidViewModel(application) {
    private val _personalTokenResponse: MutableLiveData<NetworkResult<JsonObject>> =
        MutableLiveData()
    val personalTokenResponse: LiveData<NetworkResult<JsonObject>> = _personalTokenResponse

    private val _imageSummary: MutableLiveData<NetworkResult<RepoSummary>> = MutableLiveData()
    val imageSummary: LiveData<NetworkResult<RepoSummary>> = _imageSummary

    fun fetchPersonalToken(label: String, scope: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _personalTokenResponse.postValue(NetworkResult.Loading())
            repository.getPersonalToken(label, scope).collect {
                _personalTokenResponse.postValue(it)
            }
        }
    }

    fun fetchRepositorySummary() {
        viewModelScope.launch(Dispatchers.IO) {
            _imageSummary.postValue(NetworkResult.Loading())
            repository.getImageSummary().collect {
                _imageSummary.postValue(it)
            }

        }
    }
}