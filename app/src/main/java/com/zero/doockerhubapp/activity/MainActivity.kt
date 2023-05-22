package com.zero.doockerhubapp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.navigation.Navigation
import com.zero.doockerhubapp.R
import com.zero.doockerhubapp.databinding.ActivityMainBinding
import com.zero.doockerhubapp.fragments.loginFragment.viewModel.LoginViewModel
import com.zero.doockerhubapp.utils.NetworkResult
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
//    private val loginViewModel by viewModels<LoginViewModel>()
    val TAG = "MAIN ACTIVITY"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
//        fetchData()
    }

/*    private fun fetchData() {
        val username = "zer0231"
        val password = "test123"
        loginViewModel.fetchLoginResponse(username, password)
        loginViewModel.loginResponse.observe(this) { result ->  //IN CASE OF ACTIVITY USE this INSTEAD OF viewLifeCycleOwner
            when (result) {
                is NetworkResult.Loading -> {
                    Log.d(TAG, "LOADING")
                }
                is NetworkResult.Success -> {
                    Log.d(TAG, result.data.toString())
                }
                is NetworkResult.Error -> {
                    Log.d(TAG, "${result.message}")

                }
            }

        }

    }*/
}