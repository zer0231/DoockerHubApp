package com.zero.doockerhubapp.fragments.loginFragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import com.zero.doockerhubapp.databinding.FragmentLoginBinding
import com.zero.doockerhubapp.fragments.loginFragment.viewModel.LoginViewModel
import com.zero.doockerhubapp.utils.NetworkResult

class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val loginViewModel by viewModels<LoginViewModel>()
    private val TAG = "LoginFragment"
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)


        fetchData()
        return binding.root
    }

    private fun fetchData() {
        val username = "zer0231"
        val password = "test123"
        loginViewModel.fetchLoginResponse(username, password)
        loginViewModel.loginResponse.observe(viewLifecycleOwner) { result ->
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

    }
}



